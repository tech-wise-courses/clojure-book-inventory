(ns book-inventory.core
  (:require [book-inventory.controller :as controller]))

(defn display-menu []
  (println "\nBook Inventory Menu:")
  (println "1. Add Book")
  (println "2. List Books")
  (println "3. Update Book")
  (println "4. Delete Book")
  (println "5. Search Book by Title")
  (println "6. Save Books to File")
  (println "7. Load Books from File")
  (println "8. Exit"))

(defn get-input [prompt]
  (print prompt)
  (println)
  (print "> ")
  (flush)
  (read-line))

(defn handle-choice [choice]
  (case choice
    "1" (controller/add-book (get-input "\nEnter title:")
                             (get-input "\nEnter author:")
                             (get-input "\nEnter published year:")
                             (get-input "\nEnter genre:"))
    "2" (controller/list-books)
    "3" (controller/update-book (get-input "Enter book ID:")
                                (get-input "Enter new title:")
                                (get-input "Enter new author:")
                                (get-input "Enter new published year:")
                                (get-input "Enter new genre:"))
    "4" (controller/delete-book (get-input "Enter book ID:"))
    "5" (controller/search-books :title (get-input "Enter title to search:"))
    "6" (controller/save-books "books.json")
    "7" (controller/load-books "books.json")
    "8" (do (println "Exiting...") (System/exit 0))
    (println "\n>>> Invalid choice, try again.")))

(defn start []
  (loop []
    (display-menu)
    (let [choice (get-input "\nChoose an option:")]
      (handle-choice choice)
      (recur))))

(defn -main [& args]
  (start))
