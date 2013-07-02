(ns prison-break.core)

(def action [:cooperate :defect :random])

(def history-action [:cooperate :defect :none :ignore])

(def payoff-matrix {:cooperate {:cooperate [1 1]
                                :defect    [10 0]}
                    :defect    {:cooperate [0 10]
                                :defect    [5 5]}})

(defrecord Strategy [my-action history-actions])
(defrecord Player [strategies])

(defn resolve-random 
    [action]
    "Converts :random to a concrete action"
    (if (= action :random) 
       ([:cooperate :defect] (rand-int 2)) 
       action))


(defn action-matches?
   [strategy-action history-action]
   (if (= strategy-action history-action)
      true
      (if (and (= :none strategy-action) (not history-action))
         true
         (if (some (fn [x] (= x :ignore)) [strategy-action history-action])
            true
            false
         )
      )
   )
)

(defn get-payoff
   [prisoner-a prisoner-b]
   "Calculates the payoff based on each prisoner action"
   ((payoff-matrix prisoner-a) prisoner-b)
)


(defn strategy-matches?
   [strategy history]
   "Returns true if strategy matches the supplied history"
   (loop [strat-loop strategy hist-loop history]
      (if (first strat-loop)
         (if (action-matches? (first strat-loop) (first hist-loop))
            (recur (rest strat-loop) (rest hist-loop))
            false)
         true)))

(defn find-strategy
   "Find the player's strategy based on historical moves"
   [strategies history]
   (if (strategy-matches? (:history-actions (first strategies)) history)
       (first strategies)
       (find-strategy (rest strategies) history)
   )
)

(defn run-game
   [prisoner-a history-a prisoner-b history-b]
   (let [strategy-a (find-strategy (:strategies prisoner-a) history-b) strategy-b (find-strategy (:strategies prisoner-b) history-a) ]
      [(:my-action strategy-a) (:my-action strategy-b)]
   )
)


(defn run-match
   [prisoner-a prisoner-b game-count]
   "Runs the specified number of games for the two supplied prisoners, returning their payoffs"
   (loop [payoff-a 0, payoff-b 0, games-played 0, history-a [], history-b []]
      (if (= games-played game-count)
         [payoff-a payoff-b]
         (let [strategy-a (find-strategy (:strategies prisoner-a) history-b)
               strategy-b (find-strategy (:strategies prisoner-b) history-a)
               action-a (resolve-random (:my-action strategy-a))
               action-b (resolve-random (:my-action strategy-b))
               payoffs (get-payoff action-a action-b)
               [temp-a temp-b] payoffs ]
             (recur (+ payoff-a temp-a) (+ payoff-b temp-b) (+ 1 games-played) (into [action-a] history-a) (into [action-b] history-b)) 
         )
      )
   )
)

(defn update-total-payoffs
   [prisoner-a-idx prisoner-b-idx payoffs game-result]
   "Add the game results into the payoffs vector"
   (vec (for [idx (range (count payoffs))] 
      (if (= idx prisoner-a-idx) 
         (+ (game-result 0) (payoffs idx))
         (if (= idx prisoner-b-idx)
            (+ (game-result 1) (payoffs idx))
            (payoffs idx))))))

(defn run-tournament
   [players tournament-lengh]
   (let [player-count (count players)
         game-descriptions (for [x (range player-count) y (range player-count) :while (< y x)] [x y])
         player-payoffs (vec (repeat player-count 0))]
      (loop [games game-descriptions payoffs player-payoffs] 
         (if (not (first games))  
            payoffs
            (let [[prisoner-a-idx prisoner-b-idx] (first games)
                  prisoner-a (players prisoner-a-idx)
                  prisoner-b (players prisoner-b-idx)
                  result (run-match prisoner-a prisoner-b tournament-lengh)]
                  (recur (rest games) (update-total-payoffs prisoner-a-idx prisoner-b-idx payoffs result)))))))


