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
   (println (run-tournament (create-random-population 10 5 5) 10))
)




