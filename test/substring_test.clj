(ns substring-test
  (:require [substring :as ss]
            [clojure.spec.alpha :as s]
            [clojure.test.check.generators :as gen]
            [clojure.spec.test.alpha :as stest]))

(use 'clojure.test)

(defn instr-fns [f]
  (stest/instrument `ss/strpos)
  (f)
  (stest/unstrument `ss/strpos))

(use-fixtures :once instr-fns)

(def str-gen #(gen/tuple
               (gen/fmap clojure.string/join
                         (gen/vector
                          (gen/elements ["a" "b"])))
               (gen/fmap clojure.string/join
                         (gen/vector
                          (gen/elements ["a" "b" "c"])))))

(defn pass? [t]
  (every? #(not (:failure %)) t))

(deftest check-strpos
  (is (and
       (pass? (stest/check `ss/strpos {:gen {:substring/find-args str-gen}}))
       (pass? (stest/check `ss/strpos)))))

(deftest basic
  (is (= (ss/strpos "abcd" "abcabcdabcde") 3))
  (is (= (ss/strpos "abc" "abcabcdabcde") 0))
  (is (= (ss/strpos "z" "abcabcdabcde") -1)))

(deftest empty-strings
  (is (= (ss/strpos "" "") 0))
  (is (= (ss/strpos "a" "") -1))
  (is (= (ss/strpos "" "a") 0)))

(deftest null-inputs
  (is (thrown? clojure.lang.ExceptionInfo (ss/strpos nil nil) -1)))
