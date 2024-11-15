(ns book-inventory.logic
  (:require [book-inventory.db :as db]))

(defn generate-id []
  (str (java.util.UUID/randomUUID)))

(defn create-book [title author year genre]
  {:id (generate-id)
   :title title
   :author author
   :published-year year
   :genre genre})

(defn add-book [title author year genre]
  (let [new-book (create-book title author year genre)]
    (db/add-book! new-book)
    new-book))

(defn updated-book [book-id title author year genre]
  {:title title
   :author author
   :published-year year
   :genre genre})

(defn find-book [book-id]
  (some #(when (= (:id %) book-id) %) (db/get-all-books)))

(defn search-books [field value]
  (filter #(= (get % field) value) (db/get-all-books)))
