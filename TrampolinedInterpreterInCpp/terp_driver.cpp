#include "symbol.h"
#include "lambda.h"
#include "application.h"
#include "quote.h"

#include <cassert>

class Combinators {
public:
  // Constructor.
  Combinators() :
    x(new Symbol("x")),
    y(new Symbol("y")),
    z(new Symbol("z")),
    i(new Lambda("x", x)),
    k1(new Lambda("y", x)),
    k(new Lambda("x", k1)),
    xz(new Application(x, z)),
    yz(new Application(y, z)),
    xz_yz(new Application(xz, yz)),
    s1(new Lambda("z", xz_yz)),
    s2(new Lambda("y", s1)),
    s(new Lambda("x", s2)),
    sk(new Application(s, k)),
    skk(new Application(sk, k)),
    foo(new Quote("foo")),
    bar(new Quote("bar"))
  {
  }

  void testIdentityFoo() {
    ExpressionPtr toTest(new Application(i, foo));
    assert(toTest->start() == "foo");
  }

  void testIdentityBar() {
    ExpressionPtr toTest(new Application(i, bar));
    assert(toTest->start() == "bar");
  }

  void testSkkActsLikeIdentityFoo() {
    ExpressionPtr toTest(new Application(skk, foo));
    assert(toTest->start() == "foo");
  }

  void testSkkActsLikeIdentityBar() {
    ExpressionPtr toTest(new Application(skk, bar));
    assert(toTest->start() == "bar");
  }
    
private:
  ExpressionPtr x,
    y,
    z,
    i,
    k1,
    k,
    xz,
    yz,
    xz_yz,
    s1,
    s2,
    s,
    sk,
    skk,
    foo,
    bar;
};

int main() {
  Combinators c;
  c.testIdentityFoo();
  c.testIdentityBar();
  c.testSkkActsLikeIdentityFoo();
  c.testSkkActsLikeIdentityBar();
  return 0;
}

