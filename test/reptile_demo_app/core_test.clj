(ns reptile-demo-app.core-test
  (:require [clojure.test :refer :all]
            [reptile-demo-app.core :refer :all]))

(deftest meaningless-test
  (testing "Is time a string - can we prove that theory?"
    (is (= (string? (time-now "Heisenberg"))))))


