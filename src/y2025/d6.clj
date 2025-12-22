(ns y2025.d6 
  (:require [clojure.string :as str]))

(def example
  "123 328  51 64 
 45 64  387 23 
  6 98  215 314
*   +   *   +")

(def input
  (slurp "data/y2025/d6.txt"))

(defn parse [input-str] 
  (->> (str/split input-str #"\s*\n\s*")
       (mapv (fn [s] (str/split s #"\s+")))
       (apply mapv vector)
       (mapv (fn [col-v]
               (let [nums (->> (pop col-v)
                               (mapv parse-long))
                     operator (->> (peek col-v)
                                   (get {"*" *, "+" +}))]
                 {:nums nums
                  :operator operator})))))

(reduce (fn [acc {:keys [operator nums]}]
          (+ acc (apply operator nums))
          )
        0 
        (parse input))


; map    --> 'do something to every item in the collection'
; filter --> 'check each item in the collection; keep items that pass the check'
; reduce --> 'run over the collection, accumulating something; then return it'

;; apply --> has more to do with treating the items in a coll. as an argument list

(comment 
  (parse example)

  (mapv vector [[1 2 3] [4 5 6]])
  (apply mapv vector [[1 2 3] [4 5 6]])

  ((get {"*" *, "+" +} "*") 8 4)
  *

  pop
  peek

  (* 123 45 6)

  (str ["str1" "str2" "str3"])
  (apply str ["str1" "str2" "str3"])
  (str "str1" "str2" "str3")

  reduce
  )

