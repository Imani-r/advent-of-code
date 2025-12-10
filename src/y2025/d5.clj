(ns y2025.d5 
  (:require [clojure.string :as str]))

(def example
  "3-5
10-14
16-20
12-18

1
5
8
11
17
32")

(def input (slurp "data/y2025/d5.txt"))

(defn parse [input-str]
  (let [halves (str/split input-str #"\n\n")
        ranges (->> (first halves)
                    (str/split-lines)
                    (mapv #(mapv parse-long (str/split % #"-"))))
        ids    (->> (second halves)
                   (str/split-lines)
                   (mapv parse-long))]
    {:ranges ranges
     :ids    ids}))

(defn in-range? [num [low high]]
  (and (>= num low) (<= num high)))

(defn in-any-range? [num ranges]
  (some #(in-range? num %) ranges))

(defn count-valid-ids [ids ranges]
  (count (filter #(in-any-range? % ranges) ids)))

(let [{:keys [ranges ids]} (parse input)]
  (count-valid-ids ids ranges))

(comment
  (and (<= 5 10) (<= 5 14))
  
(defn in-range? [num [low high]] 
  (and (>= num low) (<= num high)))

(in-range? 5 [3 5])   ;; => true
(in-range? 5 [10 14]) ;; => false
(in-range? 12 [10 14]) ;; => true)
)

(comment
  (let [c [1 2]
      a (first c)
      b (second c)]
  {:a a, :b b})

(let [[a b] [1 2]]
  {:a a, :b b}))

(comment
  (count (parse example))
  (count (parse input))

  (parse example)
  (:ranges (parse example))
  (:ids (parse example))

  (subs input 0 100)
  )