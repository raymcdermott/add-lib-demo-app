# add-lib demo-app - PREPL based demo

An app that can be contacted via a PREPL and have a library added at runtime.

See Alex Miller's post on [add-lib](http://insideclojure.org/2018/05/04/add-lib/)

## Pre-requisites for add-lib

1. Use deps.edn
2. Have a dependency on org.clojure/tools.deps.alpha
3. Runs using a clojure.lang.DynamicClassLoader

## Run the app 

```bash
$ clojure -A:add-lib-demo 9075
```

This project will try to start a socket server on a port given on the command line or grab an unused port if none are 
specified.

The project will keep running until you kill it / press Ctrl-C

## Connect PREPL
You can now connect up your PREPL

Here are some samples you can run to prove out the functions

```clojure 
(use 'clojure.tools.deps.alpha.repl)
(add-lib 'org.clojure/core.logic
 {:git/url "https://github.com/clojure/core.logic" 
  :sha "10ee95eb2bed70af5bc29ea3bd78b380f054a8b4"})

(use 'clojure.core.logic)
(run* [q] (== q true))
```

```clojure 
(use 'clojure.tools.deps.alpha.repl)
(add-lib 'crypto-password {:mvn/version "0.2.0"})

(require '[crypto.password.bcrypt :as password])
(def encrypted (password/encrypt "foobar"))
(password/check "foobar" encrypted) 
```

## GOTCHAs when adding libs

The add-lib only supports git SHAs for projects that have a Manifest: either deps.edn or pom.xml

You will see an error like `Manifest type not detected when finding deps for ...` when you attempt to add a 
library where these conditions are not satisfied.

## License

Copyright © 2018 Ray McDermott

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
