(ns nanogenmo.extract
  (:require
   clojure.string
   [opennlp.nlp :as nlp]))

(def get-sentences (nlp/make-sentence-detector "resources/en-sent.bin"))

(defn normalize-whitespace
  "Normalize all whitespace in the string to a single space"
  [s]
  ;;Mostly needed for Gutenberg books with newlines embedded in sentences
  (clojure.string/replace s #"\s+" " "))

(defn not-gutenberg
  [sentence]
  (not (re-matches #"(.*)?Gutenberg(.*)?" sentence)))

(defn extract
  "Extract sentences from s matching a predicate and attaching context"
  [s predicate context]
  (for [sentence (get-sentences (normalize-whitespace s))
        :when (predicate sentence)]
    (into context {:sentence sentence
                   :wordcount (count (clojure.string/split sentence #"\s"))})))

(defn extract-from-gutenberg
  "Extract sentences from a Project Gutenberg plain-text file"
  [url predicate title author]
  (let [context {:source "Project Gutenberg" :url url :title title :author author}
        predicate #(and (predicate %) (not-gutenberg %))
        ]
    (extract (slurp url) predicate context)))
