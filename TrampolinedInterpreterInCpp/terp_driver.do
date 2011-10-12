OBJECTS="application.o
  arg.o
  closure.o
  continuation.o
  done.o
  empty_environment.o
  environment.o
  expression.o
  extend_environment.o
  lambda.o
  plug.o
  quote.o
  symbol.o"
redo-ifchange terp_driver.cpp $OBJECTS
g++ -o $3 terp_driver.cpp $OBJECTS
