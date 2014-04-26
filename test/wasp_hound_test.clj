(ns wasp-hound-test
  (:use midje.sweet
        wasp-hound))

(def code-snippet 
  '(defn foo {:foo 1 :bar 2}))

(def second-code-snippet
  '(defn dupe {:foo 1}))

(fact "parses all the code we give it and produces a report about the usage of keywords"
      (detect-magic-keys code-snippet) => {:foo 1 :bar 1})

(fact "copes with multiple defs"
      (detect-magic-keys (vector code-snippet second-code-snippet)) => {:foo 2 :bar 1})
