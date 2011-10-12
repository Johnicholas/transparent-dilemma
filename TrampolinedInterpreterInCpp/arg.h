#ifndef ARG_H
#define ARG_H

#include "continuation.h"

#include <tr1/memory>

// forward declarations
class Expression;
typedef std::tr1::shared_ptr<Expression> ExpressionPtr;
class Environment;
typedef std::tr1::shared_ptr<Environment> EnvironmentPtr;
class Continuation;
typedef std::tr1::shared_ptr<Continuation> ContinuationPtr;

// A continuation that evals the argument of a function,
// then plugs it into the body of the function.
class Arg : public Continuation {
public:
  // Constructor.
  Arg(ExpressionPtr arg, EnvironmentPtr env, ContinuationPtr cont);

  // From Continuation
  std::string resume(ClosurePtr);
private:
  ExpressionPtr arg;
  EnvironmentPtr env;
  ContinuationPtr cont;
};

#endif
