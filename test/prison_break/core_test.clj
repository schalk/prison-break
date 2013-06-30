(ns prison-break.core-test
  (:require [clojure.test :refer :all]
            [prison-break.core :refer :all]))

(deftest a-test
  (testing "Test resolve-random"
    (is (not (= (resolve-random :random) :random)))))
