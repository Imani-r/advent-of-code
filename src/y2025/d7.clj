(ns y2025.d7 
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(def example
  ".......S.......
...............
.......^.......
...............
......^.^......
...............
.....^.^.^.....
...............
....^.^...^....
...............
...^.^...^.^...
...............
..^...^.....^..
...............
.^.^.^.^.^...^.
...............")

(def input
  (slurp "data/y2025/d7.txt"))

(defn parse-input [s]
  (->> (str/split-lines s)
       (mapv vec))
  )

(-> {:a 1, :b 2}
    (update ,,, :a inc)
    (update ,,, :b dec))

; Part 1
(let [[first-row & rest-rows] (parse-input example)]
      (reduce
       (fn [m row]
         #_(prn (mapv (fn [[i ch]]
                        (if (and (contains? (:beam-pos m) i)
                                 (not= \^ ch))
                          \|
                          ch))
                      (map-indexed vector row)))
         (reduce
          (fn [m pos]
            (let [ch (get row pos)]
          ; What is at pos?
              (-> m
                  (update :beam-pos #(case ch
                                   ; If dot, keep position in beam-pos set (i.e. do nothing)
                                       \. %
                                   ; If splitter, remove pos and add two new beams either side
                                       \^ (-> %
                                              (disj pos)
                                              (conj (dec pos) (inc pos)))))
              ; If splitter at current pos, inc splitter-count
                  (update :splitter-count #(if (= \^ ch) (inc %) %)))))
          m
          (:beam-pos m)))
       {:beam-pos       #{(get (set/map-invert first-row) \S)}
        :splitter-count 0} 
       rest-rows))

; Part 2
(->> (let [[first-row & rest-rows] (parse-input example)]
       (reduce
        (fn [m row]
          #_(prn (mapv (fn [[i ch]]
                         (if (and (contains? (:beam-pos m) i)
                                  (not= \^ ch))
                           \|
                           ch))
                       (map-indexed vector row)))
          (reduce
           (fn [m [pos num-paths]]
             (let [ch (get row pos)]
          ; What is at pos?
               (-> m
                   (update :beam-pos #(case ch
                                   ; If dot, keep position in beam-pos set (i.e. do nothing)
                                        \. %
                                   ; If splitter, stop tracking num of ways to get to pos,
                                   ;   carry over num of ways to positions of two new beams either side -- adding where there is overlap
                                        \^ (-> %
                                               (dissoc pos)
                                               (->> (merge-with + {(dec pos) num-paths
                                                                   (inc pos) num-paths})))))
              ; If splitter at current pos, inc splitter-count
                   (update :splitter-count #(if (= \^ ch) (inc %) %)))))
           m
           (:beam-pos m)))
        {:beam-pos       {(get (set/map-invert first-row) \S) 1}
         :splitter-count 0} 
        rest-rows))
     
     (:beam-pos)
     (vals)
     (apply +))

(parse-input example)

(comment
  
  (def first-row (first (parse-input example))))
  
  (get first-row 7)

  (->> (map-indexed vector first-row) 
       (filter #(= \S (second %)))
       (ffirst))

  (set/map-invert {:foo 1, :bar 2})

  (get (set/map-invert first-row) \S)




