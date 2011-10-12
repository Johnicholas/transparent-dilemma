#include "empty_environment.h"
#include <cassert>

// Constructor.
EmptyEnvironment::EmptyEnvironment()
{
}

void* EmptyEnvironment::lookup(std::string key) {
  assert(false and "tried to look up something in the empty environment");
  return NULL;
}

