#include "expression.h"
#include "empty_environment.h"
#include "done.h"

std::string Expression::start() {
  EnvironmentPtr empty(new EmptyEnvironment());
  ContinuationPtr done(new Done());
  return this->eval(empty, done);
}

