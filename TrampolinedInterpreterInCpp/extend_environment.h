#ifndef EXTEND_ENVIRONMENT_H
#define EXTEND_ENVIRONMENT_H

#include "environment.h"

// An environment formed by extending an existing environment,
// binding a name to a value.
class ExtendEnvironment : public Environment
{
public:
  // Constructor.
  ExtendEnvironment(std::string n, void* v, EnvironmentPtr e);
  // From Environment.
  void* lookup(std::string to_find);
private:
  std::string name;
  void* value;
  EnvironmentPtr nextEnvironment;
};

#endif

