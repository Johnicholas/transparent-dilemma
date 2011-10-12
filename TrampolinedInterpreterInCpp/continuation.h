#ifndef CONTINUATION_H
#define CONTINUATION_H

#include <string>
#include <tr1/memory>

// forward declarations
class Closure;
typedef std::tr1::shared_ptr<Closure> ClosurePtr;

// An interface that knows how to resume a computation.
class Continuation {
public:
  virtual std::string resume(ClosurePtr value)= 0;
};

#endif

