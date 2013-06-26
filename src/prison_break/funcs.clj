(ns prison-break.core)

(def action [:cooperate :defect :random])

(def history-action [:cooperate :defect :none :ignore])

(def payoff-matrix {:cooperate {:cooperate [1 1] 
                                :defect    [10 0]} 
                    :defect    {:cooperate [0 10] 
                                :defect    [5 5]}})

(defn get-payoff 
   [prisoner-a prisoner-b] 
   "Calculates the payoff based on each prisoner action"
   ((payoff-matrix prisoner-a) prisoner-b)
)



(defrecord Strategy [my-action history-actions])

(defrecord Player [strategies])



(defn run-stuff [] 
         (println (get-payoff :cooperate :cooperate))
)




