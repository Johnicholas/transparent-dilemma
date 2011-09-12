% -*-Prolog-*-
%% I am not a prolog programmer, so I will overcomment everything.

% lookup(+ToFind, +Env, -Out)
%% inefficient linear search of an association list
%% If ToFind matches Key, commit to this rule, and bind Out to Value
lookup(ToFind, extend(Key, Value, _Env), Out) :- ToFind = Key, !, Out = Value .
%% If ToFind does not match Key, commit to this rule, and recurse deeper.
lookup(ToFind, extend(Key, _Value, Env), Out) :- ToFind \= Key, !, lookup(ToFind, Env, Out) .

% ev(+Exp, +Env, +Cont, -Out)
%% To eval a sym, look the name up in the environment, and continue
ev(sym(Name), Env, K, Out) :- !, lookup(Name, Env, Val), continue(K, Val, Out) .
%% To eval a quote, continue with the quoted value
ev(quote(Quoted), _Env, K, Out) :- !, continue(K, Quoted, Out) .
%% to eval an if, eval the test, and push "decide between these branches" onto the continuation stack
ev(if(Test, TrueBranch, FalseBranch), Env, K, Out) :- !, ev(Test, Env, decide(TrueBranch, FalseBranch, Env, K), Out) .
%% to eval a lambda, bundle the formal, body, and env into a closure, ready to go when we get an actual
ev(lambda(Formal, Body), Env, K, Out) :- !, continue(K, closure(Formal, Body, Env), Out) .
%% to eval an app, eval the function, and push evalling the arg and plugging it in onto the continuation stack
ev(app(Fun, Arg), Env, K, Out) :- !, ev(Fun, Env, arg(Arg, Env, K), Out) .

% continue(+Cont, +Val, -Out)
%% To continue a done, bind Out to Val
continue(done, Val, Out) :- !, Out = Val .
%% To continue a decide where the result of the test was true, eval the true branch
continue(decide(TrueBranch, _FalseBranch, Env, K), Val, Out) :- Val = true, !, ev(TrueBranch, Env, K, Out) .
%% To continue a decide where the result of the test was false, eval the false branch
continue(decide(_TrueBranch, FalseBranch, Env, K), Val, Out) :- Val = false, !, ev(FalseBranch, Env, K, Out) .
%% To continue an arg evaluation, eval the arg and plan to plug the arg val into the proc
continue(arg(ArgExp, Env, K), Proc, Out) :- !, ev(ArgExp, Env, plug(Proc, K), Out) . 
%% To continue a plug-in, assert that Proc is indeed a closure, and eval the body in an extended environment
continue(plug(Proc, K), Actual, Out) :- !, Proc = closure(Formal, Body, E), ev(Body, extend(Formal, Actual, E), K, Out) .

% start(+Exp, -Out)
%% to start off, use an empty environment and a done continuation
start(Exp, Out) :- !, ev(Exp, empty, done, Out) .
