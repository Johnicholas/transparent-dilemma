#ifndef EXPRESSION_H
#define EXPRESSION_H

#include <string>
#include <tr1/memory>

// forward declarations
class Environment;
typedef std::tr1::shared_ptr<Environment> EnvironmentPtr;
class Continuation;
typedef std::tr1::shared_ptr<Continuation> ContinuationPtr;

// An interface
class Expression {
public:
  // Evaluate the expression, in the given environment
  virtual std::string eval(EnvironmentPtr env, ContinuationPtr k) = 0;
  std::string start();
};

typedef std::tr1::shared_ptr<Expression> ExpressionPtr;

#endif

