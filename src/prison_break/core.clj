(ns prison-break.core
  (:gen-class))

(load "funcs")

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (println "Prison Break!")
  (println action))
