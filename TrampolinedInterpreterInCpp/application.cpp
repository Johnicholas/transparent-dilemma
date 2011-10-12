#include "application.h"
#include "arg.h"

// Constructor
Application::Application(ExpressionPtr f, ExpressionPtr a) :
  fun(f),
  arg(a)
{
}

std::string Application::eval(EnvironmentPtr env, ContinuationPtr k) {
  ContinuationPtr temp(new Arg(arg, env, k));
  return fun->eval(env, temp);
}

