(ns book-inventory.io
  (:require [cheshire.core :as json]
            [clojure.java.io :as io]
            [book-inventory.db :as db]))

;; Console I/O
(defn print-book [index book]
  (println (str index ". "
                "ID: " (:id book) ", "
                "Title: " (:title book) ", "
                "Author: " (:author book) ", "
                "Year: " (:published-year book) ", "
                "Genre: " (:genre book))))

(defn print-books []
  (let [books (db/get-all-books)
        count (count books)]
    (if (empty? books)
      (println "\n>>> No books in the inventory.")
      (do
        (println (str "\n" count " Available Book" (when (> count 1) "s")))
        (println "---------------")
        (doseq [[index book] (map-indexed vector books)]
            (print-book (inc index) book))))))

;; JSON I/O
(defn save-books-to-file [file-path]
  (spit file-path (json/generate-string (db/get-all-books)))
  (println "\nBooks saved to file."))

(defn load-books-from-file [file-path]
  (try
    (let [data (slurp file-path)
          books (json/parse-string data true)]
      (db/save-books! books)
      (println "\nBooks loaded from file."))
    (catch Exception e
      (println "\nError loading books:" (.getMessage e)))))
