#ifndef ZERO_H
#define ZERO_H

#include "skew.h"

class Zero : public SkewNumber {
public:
  int toInteger();
  SkewPtr successor();
  SkewPtr successorHelper(int);
  void print();
};

#endif
