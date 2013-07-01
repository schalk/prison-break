(ns prison-break.core)

(load "game-funcs")
(load "util-funcs")
(load "evolution")

(def TitForTat 
   (Player. 
      [(Strategy. :cooperate [:none]) 
       (Strategy. :cooperate [:cooperate]) 
       (Strategy. :defect [:defect]) ]))
 
(def Random 
   (Player. 
      [(Strategy. :random []) ]))

   

(defn run-stuff [] 
   (print-player "Player 1" TitForTat)
   (let [new-player (create-random-player 10 10)] 
      (println new-player)
      (run-match new-player TitForTat 200)
   )
        
)




