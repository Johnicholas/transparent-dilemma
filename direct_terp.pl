% -*-Prolog-*-

% lookup(+ToFind, +Env, -Out)
%% inefficient linear search of an association list
%% I am not a prolog programmer, so I will overcomment everything.
%% If ToFind matches Key, commit to this rule, and bind Out to Value
lookup(ToFind, extend(Key, Value, _Env), Out) :- ToFind = Key, !, Out = Value .
%% If ToFind does not match Key, commit to this rule, and recurse deeper.
lookup(ToFind, extend(Key, _Value, Env), Out) :- ToFind \= Key, !, lookup(ToFind, Env, Out) .

% ev(+Exp, +Env, -Out)
%% To eval a sym, look the name up in the environment, and bind it to Out
ev(sym(Name), Env, Out) :- !, lookup(Name, Env, Val), Out = Val .
%% To eval a quote, bind the quoted value to Out
ev(quote(Quoted), _Env, Out) :- !, Out = Quoted .
%% to eval an if, eval the test, and then decide whether to eval the true or false branches
ev(if(Test, TrueB, FalseB), Env, Out) :- !, ev(Test, Env, Result), decide(Result, TrueB, FalseB, Env, Out) .
%% to eval a lambda, bundle the formal, body, and env into a closure and bind it to Out.
ev(lambda(Formal, Body), Env, Out) :- !, Out = closure(Formal, Body, Env) .
%% to eval an app, eval the function expression, then eval the arg expression,
%% then assert that the function really evalled to a closure,
%% and eval the body, in an extended environment that binds the formal parameter
%% to the value of the argument expression
ev(app(F, X), E, Out) :- !, ev(F, E, P), ev(X, E, V), P = closure(Formal, B, E), ev(B, extend(Formal, V, E), Out) .

% decide(+TestResult, +TrueBranch, +FalseBranch, +Env, -Out)
%% if the result was true, eval the true branch
decide(Result, TrueBranch, _FalseBranch, Env, Out) :- Result = true, !, ev(TrueBranch, Env, Out) .
%% if the result of the test was false, eval the false branch
decide(Result, _TrueBranch, FalseBranch, Env, Out) :- Result = false, !, ev(FalseBranch, Env, Out) .

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
%% to start off, use an empty environment
start(Exp, Out) :- !, ev(Exp, empty, Out) .
