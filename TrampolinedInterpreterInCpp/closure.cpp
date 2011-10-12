#include "closure.h"
#include "extend_environment.h"
#include "expression.h"

// Constructor.
Closure::Closure(std::string f, ExpressionPtr b, EnvironmentPtr e) :
  formal(f),
  body(b),
  env(e)
{
}

std::string Closure::enter(void* actual, ContinuationPtr k) {
  EnvironmentPtr temp(new ExtendEnvironment(formal, actual, env));
  return body->eval(temp, k);
}
