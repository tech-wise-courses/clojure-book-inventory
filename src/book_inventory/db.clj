(ns book-inventory.db)

(def books (atom []))

(defn get-book-by-id [book-id]
  (some #(when (= (:id %) book-id) %) @books))

(defn get-all-books []
  @books)

(defn save-books! [new-books]
  (reset! books new-books))

(defn add-book! [book]
  (swap! books conj book))

(defn update-book! [updated-book book-id]
  (swap! books (fn [books]
                 (map (fn [book]
                        (if (= (:id book) book-id)
                          (merge book updated-book)
                          book))
                      books))))

(defn delete-book! [book-id]
  (swap! books (fn [books]
                 (remove #(= (:id %) book-id) books))))
