(ns y2025.d2
  (:require [clojure.string :as str]))

(def example
  "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124")

(defn my-range [start end]
  (range (parse-long start) (+ (parse-long end) 1)))

(my-range 11 22)

(->> (str/split example #",")
     (map (fn [s]
            (apply my-range (str/split s #"-")))))
