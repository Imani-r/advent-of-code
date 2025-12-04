(ns y2025.d3 
  (:require [clojure.string :as str]))

(def example
  ["987654321111111"
   "811111111111119"
   "234234234234278"
   "818181911112111"])

(def input 
  (str/split-lines (slurp "data/y2025/d3.txt")))

(defn max-jolt [s]
  (->> s
       (map-indexed (fn [i ch1]
                      (for [ch2 (drop (inc i) s)]
                        (parse-long (str ch1 ch2)))))
       (apply concat)
       (apply max)))

(apply + (map max-jolt input))