(ns nanogenmo.core
  ( :require [nanogenmo.extract :as extract]
             [nanogenmo.persist :as persist]
             [twitter :as tw]))

(defn you?
  "Does the sentence start with a 'you'? (Less naive edition)"
  [s]
  ;;Sentence must start with punctuation and then "you", case insensitive
  ;;mostly to catch quotes, but who knows, let's see!
  (re-find #"^[^\w]*(Y|y)ou" s))

;; (def sentences (extract/extract-from-gutenberg "http://www.gutenberg.org/files/39293/39293-0.txt"
;;                                                you?
;;                                                "The Gentlemen's Book of Etiquette and
;; Manual of Politeness"
;;                                                "Cecil B. Hartley"))
;; (persist/persist-sentences sentences)
;; (println (first sentences))
;; (count sentences)
