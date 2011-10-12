#include "nonzero.h"

#include <iostream>

Nonzero::Nonzero(int rank, SkewPtr rest) :
  rank(rank),
  rest(rest)
{
}

int Nonzero::toInteger() {
  int x= (1 << (rank + 1)) - 1;
  return x + rest->toInteger();
}

void Nonzero::print() {
  int x= (1 << (rank + 1)) - 1;
  std::cout << x << " + ";
  rest->print();
}

SkewPtr Nonzero::successor() {
  return rest->successorHelper(rank);
}

SkewPtr Nonzero::successorHelper(int incoming) {
  if (incoming == rank) {
    SkewPtr answer(new Nonzero(rank+1, rest));
    return answer;
  } else {
    SkewPtr answer(new Nonzero(rank, rest));
    answer= SkewPtr(new Nonzero(incoming, answer));
    answer= SkewPtr(new Nonzero(0, answer));
    return answer;
  }
}

