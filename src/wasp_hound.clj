(ns wasp-hound
  (:gen-class))



(defn detect-magic-keys [code]
  (->> code
   (tree-seq coll? seq)
   (filter keyword?)
   (frequencies)))

(defn -main
  [x]
  (println x "Hello, World!"))
