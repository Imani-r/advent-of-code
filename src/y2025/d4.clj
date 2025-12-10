(ns y2025.d4 
  (:require [clojure.string :as str]))

(def example
  "..@@.@@@@.
   @@@.@.@.@@
   @@@@@.@.@@
   @.@@@@..@.
   @@.@@@@.@@
   .@@@@@@@.@
   .@.@.@.@@@
   @.@@@.@@@@
   .@@@@@@@@.
   @.@.@@@.@.")

(def input (slurp "data/y2025/d4.txt"))

(defn parse [input-str]
  (->> (str/split input-str #"\s+")
       (mapv vec)))

(defn neighbours [[row col]]
  [[(dec row) (dec col)] [(dec row) col] [(dec row) (inc col)] 
   [row       (dec col)] ,,,,,,,,,,,,,,, [row       (inc col)]
   [(inc row) (dec col)] [(inc row) col] [(inc row) (inc col)]])

(comment 
  (def grid (parse input))

  (get (get grid 0) 2)

  (get-in grid [0 2]) ; [row col]
  
  (get-in grid [-1 -1])

  (->> (for [row (range 0 (count grid))
             col (range 0 (count (first grid)))]
         [row col])
       (reduce (fn [removable-rolls-count [row col]]
                 (let [ch (get-in grid [row col])]
                   (if (= \@ ch)
                     (let [neighbour-rolls-count
                           (->> (neighbours [row col])
                                (map #(get-in grid %))
                                (filter #(= \@ %))
                                (count))]
                       (if (> 4 neighbour-rolls-count)
                         (inc removable-rolls-count)
                         removable-rolls-count))
                     removable-rolls-count)))
               0)) 
  
  )
