#ifndef EMPTY_ENVIRONMENT_H
#define EMPTY_ENVIRONMENT_H

#include "environment.h"

class EmptyEnvironment : public Environment {
public:
  // Constructor.
  EmptyEnvironment();
  // From Environment.
  void* lookup(std::string key);
};

#endif

