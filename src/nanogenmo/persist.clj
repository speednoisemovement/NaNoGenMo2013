(ns nanogenmo.persist
  (:require [somnium.congomongo :as mongo] ))

(def test-db-name "nanogenmo-test")
(def db-name "nanogenmo2013")

(def collection-name :sentences)

(def conn (mongo/make-connection test-db-name))



(def persist-sentences
  [sentences]
  (with-mongo conn
    (dorun (mongo/mass-insert! collection-name sentences))))
