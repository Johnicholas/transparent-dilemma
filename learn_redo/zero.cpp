#include "zero.h"
#include "nonzero.h"

#include <iostream>

int Zero::toInteger() {
  return 0;
}

SkewPtr Zero::successor() {
  SkewPtr answer(new Zero());
  answer= SkewPtr(new Nonzero(0, answer));
  return answer;
}

SkewPtr Zero::successorHelper(int rank) {
  SkewPtr answer(new Zero());
  answer= SkewPtr(new Nonzero(rank, answer));
  answer= SkewPtr(new Nonzero(0, answer));
  return answer;
}

void Zero::print() {
  std::cout << "0\n";
}
