(ns prison-break.core-test
  (:require [clojure.test :refer :all]
            [prison-break.core :refer :all]))

(deftest resolve-random-test
   (testing "Test resolve-random"
      (is (not (= (resolve-random :random) :random)))))

(deftest update-total-payoffs-test
   (testing "update payoffs vector"
      (let [payoffs (update-total-payoffs 0 3 [10 20 30 40 50] [5 10])]
         (is (= payoffs [15 20 30 50 50])))))
