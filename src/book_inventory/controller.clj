(ns book-inventory.controller
  (:require [book-inventory.db :as db]
            [book-inventory.io :as io]
            [book-inventory.logic :as logic]))

(defn add-book [title author year genre]
  (logic/add-book title author year genre)
  (println "\n>>> Book added successfully."))

(defn list-books []
  (io/print-books))

(defn update-book [book-id title author year genre]
  (if-let [book (db/get-book-by-id book-id)]
    (do
      (-> (logic/updated-book book title author year genre)
          (db/update-book! book-id))
      (println "\n>>> Book updated successfully."))
    (println "\n>>> Error: Book not found.")))

(defn delete-book [book-id]
  (if (db/get-book-by-id book-id)
    (do
      (db/delete-book! book-id)
      (println "\n>>> Book deleted successfully."))
    (println "\n>>> Error: Book not found.")))


(defn search-books [field value]
  (let [results (logic/search-books field value)]
    (if (empty? results)
      (println "\n>>> No books found.")
      (doseq [book results] (io/print-book book)))))

(defn save-books [file-path]
  (io/save-books-to-file file-path))

(defn load-books [file-path]
  (io/load-books-from-file file-path))
