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

; 1. parse the input

(->> (re-seq #"\w+" example)
     (map (fn [s]
            
             (parse-long (str/replace s #"[LR]" {"L" "-", "R" "+"})))))

(comment
  ({:a 1, :b 2} :a)
  (:a {:a 1, :b 2}))

; 2. solve the problem

