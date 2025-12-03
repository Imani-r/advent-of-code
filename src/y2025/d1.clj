(ns y2025.d1
  (:require [clojure.string :as str]))

(def example
  "L68
   L30
   R48
   L5
   R60
   L55
   L1
   L99
   R14
   L82")

(def input
  (slurp "data/y2025/d1.txt"))

(defn parse [input]
  (->> (re-seq #"\w+" input)
       (map (fn [s]
              (parse-long (str/replace s #"[LR]" {"L" "-", "R" "+"}))))))

(comment
  ; Part 1
  (reduce
   (fn [[pos zeros-count] n] 
     (let [new-pos (mod (+ pos n) 100)]
       [new-pos (if (zero? new-pos)
                  (inc zeros-count)
                  zeros-count)]))
   [50, 0]
   (parse input)
   )

  ; Part 2  
  (reduce
   (fn [[pos tot] n]
     (let [new-pos (mod (+ pos n) 100)
           clicks (count
                    (filter #(zero? (mod % 100)) 
                           (range pos (+ pos n) (if (neg? n) -1 1))))]
       [new-pos (+ tot clicks)]))
   [50, 0]
   (parse input)) 
  
  )


