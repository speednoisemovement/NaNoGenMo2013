(ns nanogenmo.extract
  (:require
   clojure.string
   [opennlp.nlp :as nlp]))

(def get-sentences (nlp/make-sentence-detector "resources/en-sent.bin"))

(defn extract
  "Extract sentences from s matching a predicate and attaching context"
  [s predicate context]
  (for [sentence (get-sentences s) :when (predicate sentence)]
    (into context {:sentence sentence
                   :words (count (clojure.string/split sentence #"\s"))})
    )
  )
