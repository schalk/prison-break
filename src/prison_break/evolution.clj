(ns prison-break.core)

(defn create-random-strategy
   [max-history]
   (Strategy.
      ([:cooperate :defect :random] (rand-int 3))
      (vec (repeatedly (+ 1 (rand-int (- max-history 1))) #([:cooperate :defect :ignore] (rand-int 3))))))

(defn create-random-player 
   [max-size max-history]
   "Creates a random strategy"
   (let [default-strat [([:cooperate :defect :random] (rand-int 3))]
        strategy-size (rand-int (- max-size 1))]
      (loop [strategies [(Strategy. default-strat [])] idx 0]
         (if (= strategy-size idx)
            (Player. (sort (comparator (fn [x y] (> (count (:history-actions x)) (count (:history-actions y))))) strategies))
            (recur (into [(create-random-strategy max-history)] strategies) (+ idx 1) )))))
        
   

