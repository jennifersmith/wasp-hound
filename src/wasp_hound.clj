(ns wasp-hound
  (:gen-class)
  (:refer-clojure :exclude [read read-string *default-data-reader-fn* *read-eval* *data-readers*])
  (:require
   [clojure.tools.reader.reader-types :as t]
   [clojure.tools.reader :refer 
    [read read-string *default-data-reader-fn* *read-eval* *data-readers*]]))



(defn detect-magic-keys [code]
  (->> code
   (tree-seq coll? seq)
   (filter keyword?)
   (frequencies)
   (filter (comp #(> % 1) second))
   (sort-by second)
   (reverse)))

(defn read-all [code-string]
  (let [pushback-reader (t/string-push-back-reader code-string)]
    (loop [result []]
      (if-let [next (read pushback-reader false nil)]
        (recur (conj result next))
        result))))

(defn magic-keys-in-file [file]
  (let [
        code-string (slurp file)
        code (read-all code-string)]
    (detect-magic-keys code)))

(defn -main
  [x]
  (println x "Hello, World!"))
