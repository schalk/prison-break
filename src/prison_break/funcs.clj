(ns prison-break.core)

(load "game-funcs")


(def TitForTat (Player. [(Strategy. :cooperate [:none]) (Strategy. :cooperate [:cooperate]) (Strategy. :defect [:defect]) ]))
(def Random (Player. [(Strategy. :random []) ]))

   

(defn run-stuff [] 
         (println (:my-action (find-strategy (:strategies TitForTat) [:cooperate :cooperate])))
)




