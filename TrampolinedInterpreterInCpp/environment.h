#ifndef ENVIRONMENT_H
#define ENVIRONMENT_H

#include <string>

// An environment is a thing you can look names up in to get values.
class Environment {
public:
  // The reason this class exists.
  virtual void* lookup(std::string key) = 0;
  // Destructor.
  ~Environment() {}
protected:
  // Constructor.
  Environment() {}
};

#endif


