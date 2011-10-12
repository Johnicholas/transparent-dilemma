#ifndef APPLICATION_H
#define APPLICATION_H

#include "expression.h"

class Application : public Expression {
public:
  // Constructor.
  Application(ExpressionPtr f, ExpressionPtr a);
  std::string eval(EnvironmentPtr env, ContinuationPtr k);
private:
  ExpressionPtr fun;
  ExpressionPtr arg;
};

#endif

