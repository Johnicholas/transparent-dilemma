#!/usr/bin/gst -f

Object subclass: Env [
  <comment: 'Environments are things that you can look stuff up in.'>
]

Env subclass: Empty [
  <comment: 'This is an empty environment:
  if you try to find anything in it, there is an error.
  Ideally, there would be nice error reporting, but for right now,
  the error reporting that Smalltalk provides is far better than
  anything that I could provide.'>
]

Env subclass: Extend [
  | name value env |

  <comment: 'A nonempty environment, extending a previous environment
  by binding one name to one value.'>

  Extend class >> name: n value: v env: e [ 
    "Answer a new Env extending e, binding n to v, overriding any previous bindings to n."

    <category: 'instance creation'>
    ^((self new) setName: n setValue: v setEnv: e)
  ]

  setName: n setValue: v setEnv: e [
    "A mutator, intended to be used only from the constructor.
    I don't know how to do immutable objects properly in Smalltalk."

    <category: 'private'>
    name := n .
    value := v .
    env := e .
  ]

  lookup: toFind [
    <category: 'accessing'>
    ^(
      (toFind == name)
        ifTrue: [
          ^value
        ]
        ifFalse: [
          ^(env lookup: toFind)
        ]
     )
  ]
]

Object subclass: Cont [
  <comment: 'An abstract class for things that know how to continue on, with a value.'>
]

Cont subclass: Done [
  <comment: 'Done is a continuation that just returns whatever it is handed.'>
  continue: value [
    ^value
  ]
]

Object subclass: Exp [
  <comment: 'An abstract class for things that know how to evaluate themselves.
  subclasses should respond to the ev method.'>

  start [
    ^(self ev: (Empty new) andThen: (Done new))
  ]
]

Exp subclass: Sym [
  | name |

  <comment: 'An exp that looks up a name in the environment.'>

  Sym class >> name: aName [
    <category: 'instance creation'>
    ^((self new) setName: aName)
  ]

  setName: aName [
    "A mutator, intended to be used only from the constructor.
    I don't know how to do immutable objects properly in Smalltalk."

    <category: 'private'>
    name := aName
  ]

  ev: env andThen: k [
    ^(k continue: (env lookup: name))
  ] 

  printOn: out [ out nextPutAll: ('Sym(%1)' % {name}) ]
]

Exp subclass: Quote [
  | quoted |

  <comment: 'An exp that just returns something.'>

  Quote class >> quoted: q [
    <category: 'instance creation'>
    ^((self new) setQuoted: q)
  ]

  setQuoted: q [
    "A mutator, intended to be used only from the constructor.
    I don't know how to do immutable objects properly in Smalltalk."

    <category: 'private'>
    quoted := q
  ]

  ev: env andThen: k [
    ^(k continue: quoted)
  ]

  printOn: out [ out nextPutAll: ('Quote(%1)' % {quoted}) ]
]

Cont subclass: Decide [
  | trueBranch falseBranch env cont |
  
  <comment: 'The continuation of an If, after the test has been evalled.'>

  Decide class >> trueBranch: tb falseBranch: fb env: e cont: k [
    ^((self new) setTrueBranch: tb setFalseBranch: fb setEnv: e setCont: k)
  ]

  setTrueBranch: tb setFalseBranch: fb setEnv: e setCont: k [
    "A mutator, intended to be used only from the constructor.
    I don't know how to do immutable objects properly in Smalltalk."

    <category: 'private'>
    trueBranch := tb .
    falseBranch := fb .
    env := e .
    cont := k .
  ]

  continue: result [
    "In straightforward metacircular-evaluator fashion, we use Smalltalk's booleans to implement If."

    ^(result ifTrue: [
        ^(trueBranch ev: env andThen: cont)
      ] ifFalse: [
        ^(falseBranch ev: env andThen: cont)
      ]
    )
  ]
]

Exp subclass: If [
  | test trueBranch falseBranch |

  <comment: 'An exp that evals a test to decide which of two branches to eval and return.'>

  If class >> test: t trueBranch: tb falseBranch: fb [
    ^((self new) setTest: t setTrueBranch: tb setFalseBranch: fb)
  ]

  setTest: t setTrueBranch: tb setFalseBranch: fb [
    "A mutator, intended to be used only from the constructor.
    I don't know how to do immutable objects properly in Smalltalk."

    <category: 'private'>
    test := t .
    trueBranch := tb .
    falseBranch := fb .
  ]

  ev: env andThen: k [
    "First we eval the test, and then decide whether to eval the true or false branch."

    ^(test ev: env andThen: (Decide trueBranch: trueBranch falseBranch: falseBranch env: env cont: k))
  ]

  printOn: out [ out nextPutAll: ('If(%1, %2, %3)' % {test. trueBranch. falseBranch}) ]
]

Object subclass: Closure [
  | formal body env |

  <comment: 'Something that enters the body of a function, once the actual argument is available.'>

  Closure class >> formal: f body: b env: e [
    <category: 'instance creation'>
    ^((self new) setFormal: f setBody: b setEnv: e)
  ]

  setFormal: f setBody: b setEnv: e [
    "A mutator, intended to be used only from the constructor.
    I don't know how to do immutable objects properly in Smalltalk."

    <category: 'private'>
    formal := f .
    body := b .
    env := e .
  ]

  enter: actual andThen: k [
    <comment: 'Once we have an actual, we can bind it to the formal and go on.'>

    ^(body ev: (Extend name: formal value: actual env: env) andThen: k)
  ]
] 

Exp subclass: Lambda [
  | name body |

  <comment: 'An exp that is a function.'>

  Lambda class >> name: n body: b [
    <category: 'instance creation'>
    ^((self new) setName: n setBody: b)
  ]

  setName: n setBody: b [
    "A mutator, intended to be used only from the constructor.
    I don't know how to do immutable objects properly in Smalltalk."

    <category: 'private'>
    name := n .
    body := b .
  ]

  ev: env andThen: k [
    "Bundle the environment with everything else, and continue."

    ^(k continue: (Closure formal: name body: body env: env))
  ]

  printOn: out [ out nextPutAll: ('Lambda(%1, %2)' % {name. body}) ]
]

Cont subclass: Enter [
  | proc cont |

  <comment: 'A continuation that plugs an evalled arg into an evalled closure.'>

  Enter class >> proc: p cont: k [
    <category: 'instance creation'>
    ^((self new) setProc: p setCont: k)
  ]

  setProc: p setCont: k [
    "A mutator, intended to be used only from the constructor.
    I don't know how to do immutable objects properly in Smalltalk."

    <category: 'private'>
    proc := p .
    cont := k .
  ]

  continue: actual [
    <comment: 'Now we can plug in and enter the body.'>

    ^(proc enter: actual andThen: cont)
  ]
]  

Cont subclass: MyArg [
  | arg env cont |

  <comment: 'A continuation that evals the argument of a function, then plugs it into the body.'>

  MyArg class >> arg: a env: e cont: k [
    <category: 'instance creation'>
    ^((self new) setArg: a setEnv: e setCont: k)
  ]

  setArg: a setEnv: e setCont: k [
    "A mutator, intended to be used only from the constructor.
    I don't know how to do immutable objects properly in Smalltalk."

    <category: 'private'>
    arg := a .
    env := e .
    cont := k .
  ]

  continue: proc [
    <comment: 'Now we eval the arg, next we can plug in and enter the body.'>

    ^(arg ev: env andThen: (Enter proc: proc cont: cont))
  ]
]  

Exp subclass: App [
  | fun arg |

  <comment: 'An exp that is a function application.'>

  App class >> fun: f arg: a [
    <category: 'instance creation'>
    ^((self new) setFun: f setArg: a)
  ]

  setFun: f setArg: a [
    <comment: 'A mutator, intended to be used only from the constructor.
    I do not know how to do immutable objects properly in Smalltalk.'>

    <category: 'private'>
    fun := f .
    arg := a .
  ]

  ev: env andThen: k [
    <comment: 'To eval a function application,
    first eval the function,
    then eval the arg,
    then enter the function body, plugging in the value of the arg.'>

    ^(fun ev: env andThen: (MyArg arg: arg env: env cont: k))
  ]

  printOn: out [ out nextPutAll: ('App(%1, %2)' % {fun. arg}) ]
]



"Misc exploratory/irregular/insufficient tests"
a := Sym name: #x .
a printOn: stdout .
stdout nl .
x123 := Extend name: #x value: 123 env: (Empty new)
(x123 lookup: #x) printOn: stdout .
stdout nl .
y456 := Extend name: #y value: 456 env: x123
(y456 lookup: #x) printOn: stdout .
stdout nl .
(y456 lookup: #y) printOn: stdout .
stdout nl .
"By contrast, this would error, because the lookup would go through to the empty --> (x123 lookup: #y) printOn: stdout ."

(Quote quoted: #z) printOn: stdout .
stdout nl .
(If test: #foo trueBranch: #bar falseBranch: #baz) printOn: stdout .
stdout nl .
(Lambda name: #x body: (Sym name: #x)) printOn: stdout .
stdout nl .

i := Lambda name: #x body: (Sym name: #x) .
(App fun: i arg: i) printOn: stdout .
stdout nl .
result := (App fun: i arg: (Quote quoted: 123)) start .
result printOn: stdout .
stdout nl .

w := (Lambda name: #x body: (App fun: (Sym name: #x) arg: (Sym name: #x))) .
loop := (App fun: w arg: w) .
"This does not terminate: loop start"
((If test: (Quote quoted: true) trueBranch: (Quote quoted: 456) falseBranch: loop) start) printOn: stdout .
stdout nl .
((If test: (Quote quoted: false) trueBranch: loop falseBranch: (Quote quoted: 789)) start) printOn: stdout .
stdout nl .

