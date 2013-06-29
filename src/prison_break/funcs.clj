(ns prison-break.core)

(load "game-funcs")
(load "util-funcs")

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
         (println (run-match TitForTat TitForTat 200))
         (println (run-match Random Random 200))
)




