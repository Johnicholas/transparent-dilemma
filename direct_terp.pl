% -*-Prolog-*-
%% I am not a prolog programmer, so I will overcomment everything.

% lookup(+ToFind, +Env, -Out)
%% inefficient linear search of an association list
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
%% then assert that the function expression evalled to a closure,
%% and eval the body, in an extended environment that binds the formal parameter
%% to the value of the argument expression
ev(app(F, X), E, Out) :- !, ev(F, E, P), ev(X, E, V), P = closure(Formal, B, E), ev(B, extend(Formal, V, E), Out) .

% decide(+TestResult, +TrueBranch, +FalseBranch, +Env, -Out)
%% if the result was true, eval the true branch
decide(Result, TrueBranch, _FalseBranch, Env, Out) :- Result = true, !, ev(TrueBranch, Env, Out) .
%% if the result of the test was false, eval the false branch
decide(Result, _TrueBranch, FalseBranch, Env, Out) :- Result = false, !, ev(FalseBranch, Env, Out) .

% start(+Exp, -Out)
%% to start off, use an empty environment
start(Exp, Out) :- !, ev(Exp, empty, Out) .
