(ns substring
  (:require [clojure.string :as str]
            [clojure.spec.alpha :as spec]))

(defn- substring [text start len]
  (let [c (.toCharArray text)]
    (str/join (map #(nth c %) (range start (+ start len))))))

(defn- slider
  ([text window-len] (slider text window-len 0))
  ([text window-len cur] (lazy-seq
                          (when (<= (+ cur window-len) (.length text))
                            (cons (substring text cur window-len)
                                  (slider text window-len (inc cur)))))))

(spec/def :substring/find-args (spec/cat :needle string? :haystack string?))

(spec/fdef strpos
  :args :substring/find-args
  :fn #(and (<= (:ret %) (.length (-> % :args :haystack)))
            (or (>= (:ret %) 0) (= -1 (:ret %))))
  :ret int?)

(defn strpos [needle haystack]
  "Search for needle as a substring of haystack.
   Return the first position found or -1 if not present."
  (-> (loop [p 0 [fst & rst] (slider haystack (.length needle))]
        (cond
          (= fst needle) p
          rst (recur (inc p) rst)
          :else -1))))
