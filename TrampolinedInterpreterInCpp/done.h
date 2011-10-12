#ifndef DONE_H
#define DONE_H

#include "continuation.h"

class Done : public Continuation {
public:
  // Constructor.
  Done();
  // From Continuation.
  std::string resume(ValuePtr value);
};

#endif

  
  
