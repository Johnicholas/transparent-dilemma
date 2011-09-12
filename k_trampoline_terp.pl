% -*-Prolog-*-
%% I am not a prolog programmer, so I will overcomment everything.
%% this is a simple interpreter, in 'trampolined style',
%% based on the Krivine machine instead of the CEK machine.
%% TODO: figure out how to do uncurried functions like (f x y z)

% ev(+Code, +Env, +Stack, -Out)
%% an expression can be a symbol.
%% the value of a symbol is looked up in the environment.
%% note that the "value of a symbol" is not really a value -
%% we cannot use it immediately. Rather, we have to eval it.
ev(sym(N1), extend(N2, closure(C, E1), _E2), S, Out) :- N1 = N2, !, ev(C, E1, S, Out).
%% if the names don't match, then keep looking deeper in the environment.
ev(sym(N1), extend(N2, _Closure, E2), S, Out) :- N1 \= N2, !, ev(sym(N1), E2, S, Out).

%% an expression can be a quote
%% to eval a quote, take the quotes off and return whatever is inside
%% but the stack had better be nil - otherwise we're trying to apply
%% something that is not a function.
ev(quote(X), _E, nil, Out) :- !, Out = X.

%% an expression can be a lambda
%% If we're evalling a lambda, there should be something on the stack.
%% We bind the formal to the actual and continue into the body.
ev(lambda(Formal, Body), E, cons(Actual, Stack), Out) :- !, ev(Body, extend(Formal, Actual, E), Stack, Out).

%% an expression can be a function application
%% We just push the argument, together with
%% the environment we'll need to eval it,
%% onto the stack for later.
ev(app(F, X), E, Stack, Out) :- !, ev(F, E, cons(closure(X, E), Stack), Out).

% to start, use the empty environment and empty stack.
start(Code, Out) :- !, ev(Code, empty, nil, Out).

