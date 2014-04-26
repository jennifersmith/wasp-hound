(ns wasp-hound-test
  (:use midje.sweet
        wasp-hound))

(def code-snippet 
  (vector
     '(def foo {:foo 1 :wibble 2})
     '(def bar {:foo 1})))

(def dupey-code-snippet 
  (vector
   '(defn more [{:foo foo :wibble wibble}] (inc foo))
   '(defn yet-more [{:wibble bar}] (inc bar))
   '(def foo {:wibble 2})
   '(def bar {:foo 1})))

(fact "parses all the code we give it and produces a report about multi usage of keywords"
      (detect-magic-keys code-snippet) => [[:foo 2]])

(fact "order by occurrences"
      (detect-magic-keys dupey-code-snippet) => [[:wibble 3] [:foo 2]])

(fact "find magic keys on a file"
      (magic-keys-in-file "fixtures/some-code/file1.clj") => [[:bar 3]])


