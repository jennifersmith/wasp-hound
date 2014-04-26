(ns wasp-hound
  (:gen-class))



(defn detect-magic-keys [code]
  (->> code
   (tree-seq coll? seq)
   (filter keyword?)
   (frequencies)
   (filter (comp #(> % 1) second))
   (sort-by second)
   (reverse)))

(defn -main
  [x]
  (println x "Hello, World!"))
