(ns prison-break.core)

(def action [:cooperate :defect :random])

(def history-action [:cooperate :defect :none :ignore])

(def payoff-matrix {:cooperate {:cooperate [1 1]
                                :defect    [10 0]}
                    :defect    {:cooperate [0 10]
                                :defect    [5 5]}})

(defrecord Strategy [my-action history-actions])
(defrecord Player [strategies])


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
   (let [handle-random (fn [x] (if (= x :random) ([:cooperate :defect] (rand-int 2)) x))]
      ((payoff-matrix (handle-random prisoner-a)) (handle-random prisoner-b))
   )
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
         '(payoff-a payoff-b)
         (let [strategy-a (find-strategy (:strategies prisoner-a))] 
            (let [strategy-b (find-strategy (:strategies prisoner-b))]
               
            )
         )
      )
   )
)

