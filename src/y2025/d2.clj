(ns y2025.d2
  (:require [clojure.string :as str]))

(def example
  "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124")

(def input
  (slurp "data/y2025/d2.txt"))


(defn my-range [start end]
  (range (parse-long start) (+ (parse-long end) 1)))

; Part 1

;; Checks if a number's digits repeat halfway through
;; e.g., 1111 -> "11" = "11", 38593859 -> "3859" = "3859"
(defn repeats-twice? [n]
  (let [s (str n)                      ; Convert number to string
        len (count s)                  ; Get total length
        half (/ len 2)                 ; Find midpoint
        first-half (subs s 0 half)     ; Extract first half
        second-half (subs s half)]     ; Extract second half
    (= first-half second-half)))       ; Check if halves match

(->> (str/split input #",")                              ; Split by commas into ranges
     (map (fn [s]                                        ; For each range string
            (apply my-range (str/split s #"-"))))        ; Split on "-" and create range
     flatten                                             ; Combine all ranges into single list
     (filter (fn [n] (even? (count (str n)))))          ; Keep only numbers with even digit count
     (filter repeats-twice?)                            ; Keep only numbers where halves repeat
     (apply +))                                         ; Sum all remaining numbers

