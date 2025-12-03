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

;; Parse input string into list of signed numbers
;; Converts "L68" -> -68 and "R48" -> +48
(defn parse [input]
  (->> (re-seq #"\w+" input)                                      ; Extract all words (L68, R30, etc.)
       (map (fn [s]
              (parse-long (str/replace s #"[LR]" {"L" "-", "R" "+"})))))) ; Replace L with -, R with +, then parse

(comment
  ; Part 1
  ;; Track position on circular track (0-99) and count how many times we hit position 0
  (reduce
   (fn [[pos zeros-count] n]                ; Accumulator: [current-position, count-of-zeros]
     (let [new-pos (mod (+ pos n) 100)]     ; Move by n, wrap around at 100
       [new-pos (if (zero? new-pos)         ; If we landed on 0
                  (inc zeros-count)         ; Increment the zero counter
                  zeros-count)]))           ; Otherwise keep count the same
   [50, 0]                                  ; Start at position 50, with 0 zeros counted
   (parse input))

  ; Part 2  
  ;; Track position and count total number of times we cross position 0 during moves
  (reduce
   (fn [[pos tot] n]                        ; Accumulator: [current-position, total-crossings]
     (let [new-pos (mod (+ pos n) 100)      ; Calculate new position after move
           clicks (count                    ; Count crossings during this move
                    (filter #(zero? (mod % 100))           ; Check which positions are divisible by 100
                           (range pos (+ pos n) (if (neg? n) -1 1))))]  ; Generate range of positions moved through
       [new-pos (+ tot clicks)]))           ; Update position and add crossings to total
   [50, 0]                                  ; Start at position 50, with 0 crossings
   (parse input)) 
  
  )