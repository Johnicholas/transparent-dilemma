% -*-Prolog-*-
%% I am not a prolog programmer, so I will overcomment everything.

% lookup(+ToFind, +Env, -Out)
%% inefficient linear search of an association list
%% If ToFind matches Key, commit to this rule, and bind Out to Value
lookup(ToFind, extend(Key, Value, _Env), Out) :- ToFind = Key, !, Out = Value .
%% If ToFind does not match Key, commit to this rule, and recurse deeper.
lookup(ToFind, extend(Key, _Value, Env), Out) :- ToFind \= Key, !, lookup(ToFind, Env, Out) .

% ev(+Exp, +Env, +Cont, +Fuel, -Out)
%% This just handles the fuel, using peano integers.
%% I don't understand or want to be trapped by native arithmetic in Prolog,
%% and this provides a more obvious path for trying to get Fuel to be an output.
ev(Exp, Env, K, s(Fuel), Out) :- !, go(Exp, Env, K, Fuel, Out) .
ev(Exp, Env, K, z, Out) :- !, Out = exhausted(Exp, Env, K) .

% go(+Exp, +Env, +Cont, +Fuel, -Out)
%% To eval a sym, look the name up in the environment, and continue
go(sym(Name), Env, K, Fuel, Out) :- !, lookup(Name, Env, Val), continue(K, Val, Fuel, Out) .
%% To eval a quote, continue with the quoted value
go(quote(Quoted), _Env, K, Fuel, Out) :- !, continue(K, Quoted, Fuel, Out) .
%% to eval an if, eval the test, and push "decide between these branches" onto the continuation stack
go(if(Test, TrueB, FalseB), Env, K, Fuel, Out) :- !, ev(Test, Env, decide(TrueB, FalseB, Env, K), Fuel, Out) .
%% to eval a lambda, bundle the formal, body, and env into a closure, ready to go when we get an actual
go(lambda(Formal, Body), Env, K, Fuel, Out) :- !, continue(K, closure(Formal, Body, Env), Fuel, Out) .
%% to eval an app, eval the function, and push evalling the arg and plugging it in onto the continuation stack
go(app(Fun, Arg), Env, K, Fuel, Out) :- !, ev(Fun, Env, arg(Arg, Env, K), Fuel, Out) .

% continue(+Cont, +Val, +Fuel, -Out)
%% To continue a done, bind Out to Val
continue(done, Val, Remaining, Out) :- !, Out = done(Remaining, Val) .
%% To continue a decide where the result of the test was true, eval the true branch
continue(decide(TrueBranch, _FalseBranch, Env, K), Val, Fuel, Out) :- Val = true, !, ev(TrueBranch, Env, K, Fuel, Out) .
%% To continue a decide where the result of the test was false, eval the false branch
continue(decide(_TrueBranch, FalseBranch, Env, K), Val, Fuel, Out) :- Val = false, !, ev(FalseBranch, Env, K, Fuel, Out) .
%% To continue an arg evaluation, eval the arg and plan to plug the arg val into the proc
continue(arg(ArgExp, Env, K), Proc, Fuel, Out) :- !, ev(ArgExp, Env, plug(Proc, K), Fuel, Out) . 
%% To continue a plug-in, assert that Proc is indeed a closure, and eval the body in an extended environment
continue(plug(Proc, K), Actual, Fuel, Out) :- !, Proc = closure(Formal, Body, E), ev(Body, extend(Formal, Actual, E), K, Fuel, Out) .

% start(+Exp, +Fuel, -Out)
%% to start off, use an empty environment and a done continuation
start(Exp, Fuel, Out) :- !, ev(Exp, empty, done, Fuel, Out) .
