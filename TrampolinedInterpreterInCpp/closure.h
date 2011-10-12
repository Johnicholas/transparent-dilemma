#ifndef CLOSURE_H
#define CLOSURE_H

#include <string>
#include <tr1/memory>

// forward declarations
class Expression;
typedef std::tr1::shared_ptr<Expression> ExpressionPtr;
class Environment;
typedef std::tr1::shared_ptr<Environment> EnvironmentPtr;
class Continuation;
typedef std::tr1::shared_ptr<Continuation> ContinuationPtr;

// A closure is something like a half-finished function application.
// It has the formal parameter name, the environment, and the function body,
// but it doesn't have the actual parameter.
class Closure {
public:
  // Constructor
  Closure(std::string f, ExpressionPtr b, EnvironmentPtr e);

  std::string enter(void* actual, ContinuationPtr k);
private:
  std::string formal;
  ExpressionPtr body;
  EnvironmentPtr env;
};

#endif

