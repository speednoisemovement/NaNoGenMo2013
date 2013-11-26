(ns nanogenmo.generate
  (:use [clojure.string :only [join]]))

(defn as-paragraphs
  [sentences]
  (loop [coll sentences
         acc []]
    (let [[graf pool] (split-at (+ 2 (rand-int 5)) coll)]
      (if (seq graf)
        (recur pool (conj acc graf))
        acc))))

(defn generate-paragraphs
  [sentences]
  (let [texts (map :sentence sentences)
        chunks (as-paragraphs texts)
        paragraphs (map #(join " " %) chunks)]
    paragraphs))

(defn generate
  [sentences]
  (join \newline (generate-paragraphs sentences)))

(defn generate-html
  [sentences]
  (->> sentences
       generate-paragraphs
       (map #(str "<p>" % "</p>"))
       (join \newline)))
