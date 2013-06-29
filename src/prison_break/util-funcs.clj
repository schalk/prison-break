(ns prison-break.core)

(defn print-player 
   [description player]
   "Prints a nicely formatted description of a player"
   (println "Player " description)
   (map 
      (fn [x] 
         (do 
            (println (str " ---"))
            (println (str "  Action : " (:my-action x)))
            (println (str "  History: " (:history-actions x)))))
      (:strategies player))) 
