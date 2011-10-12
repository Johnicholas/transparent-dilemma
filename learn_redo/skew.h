#ifndef SKEW_H
#define SKEW_H

#include <tr1/memory>

class SkewNumber;
typedef std::tr1::shared_ptr<SkewNumber> SkewPtr;

// An interface for skew numbers.
class SkewNumber {
public:
  virtual int toInteger() = 0;
  virtual SkewPtr successor() = 0;
  virtual SkewPtr successorHelper(int) = 0;
  virtual void print() = 0;
  // Destructor.
  virtual ~SkewNumber() {}
protected:
  // Constructor.
  SkewNumber() {}
};

#endif
