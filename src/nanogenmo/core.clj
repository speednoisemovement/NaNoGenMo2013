(ns nanogenmo.core
  ( :require [nanogenmo.extract :as extract]
             [nanogenmo.persist :as persist]
 ))

;; (defn you?
;;   "Does the sentence start with a you? (Extra naive edition)"
;;   [s]
;;   (= "You" (subs s 0 3)))

(defn you?
  "Does the sentence start with a 'you'? (Less naive edition)"
  [s]
  ;;Sentence must start with punctuation and then "you", case insensitive
  ;;mostly to catch quotes, but who knows, let's see!
  (re-find #"^[^\w]*(Y|y)ou" s))

(def book-url "http://www.gutenberg.org/cache/epub/844/pg844.txt")
(def sentences (extract/extract-from-gutenberg book-url you? "The Importance of Being Earnest" "Oscar Wilde"))
(persist/persist sentences)
