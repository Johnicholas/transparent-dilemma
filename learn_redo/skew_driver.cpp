#include "zero.h"

#include <iostream>

int main(int argc, char* argv[]) {
  std::cout << "Hello\n";
  {
    SkewPtr counter(new Zero());
    std::cout << counter->toInteger() << "\n";
    for (int i= 0; i < 16; ++i) {
      counter= counter->successor();
      std::cout << counter->toInteger() << " = ";
      counter->print();
    }
  }
  std::cout << "Goodbye\n";
  return 0;
}
