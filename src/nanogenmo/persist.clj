(ns nanogenmo.persist
  (:require [somnium.congomongo :as mongo] ))

(def test-db-name "nanogenmo-test")
(def db-name "nanogenmo2013")

(def collection-name :sentences)

(def conn (mongo/make-connection test-db-name))
(println conn)
(def fetched (mongo/with-mongo conn
               (mongo/fetch :sentences :sort {:wordcount -1})))

(defn persist-sentences
  [sentences]
  (mongo/with-mongo conn
    (dorun (mongo/mass-insert! collection-name sentences))))
