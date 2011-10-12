#include "arg.h"
#include "plug.h"
#include "expression.h"

// Constructor
Arg::Arg(ExpressionPtr arg, EnvironmentPtr env, ContinuationPtr cont) :
  arg(arg),
  env(env),
  cont(cont)
{
}

std::string Arg::resume(ClosurePtr proc) {
  // proc had better be a closure.
  ContinuationPtr temp(new Plug(proc, cont));
  return arg->eval(env, temp);
}

