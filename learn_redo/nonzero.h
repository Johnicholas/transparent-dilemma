#ifndef NONZERO_H
#define NONZERO_H

#include "skew.h"

class Nonzero : public SkewNumber {
public:
  // Constructor.
  Nonzero(int rank, SkewPtr rest);
  // From SkewNumber.
  int toInteger();
  SkewPtr successor();
  SkewPtr successorHelper(int);
  void print();
private:
  int rank;
  SkewPtr rest;
};

#endif
