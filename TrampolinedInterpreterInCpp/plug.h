#ifndef PLUG_H
#define PLUG_H

#include "continuation.h"

#include <tr1/memory>

// A continuation that plugs an evalled arg into an evalled closure.
class Plug : public Continuation {
public:
  // Constructor.
  Plug(ClosurePtr p, ContinuationPtr k);

  // From Continuation.
  std::string resume(ClosurePtr);
private:
  ClosurePtr proc;
  ContinuationPtr cont;
};

#endif
