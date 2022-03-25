(ns substring
  (:require [clojure.string :as str]
            [clojure.spec.alpha :as spec]))

(defn- substring [^String text start len]
  (let [c (.toCharArray text)]
    (str/join (map #(nth c %) (range start (+ start len))))))

(defn- slider
  ([^String text window-len] (slider text window-len 0))
  ([^String text window-len cur] (lazy-seq
                          (when (<= (+ cur window-len) (.length text))
                            (cons (substring text cur window-len)
                                  (slider text window-len (inc cur)))))))

(spec/def :substring/find-args (spec/cat :needle string? :haystack string?))

(spec/fdef strpos
  :args :substring/find-args
  :fn #(let [r (:ret %)]
         (and (<= r (.length (-> % :args :haystack)))
              (or (>= r 0) (= r -1))))
  :ret int?)

(defn ^Long strpos [^String needle ^String haystack]
  "Search for needle as a substring of haystack.
   Return the first position found or -1 if not present."
  (-> (loop [p 0 [fst & rst] (slider haystack (.length needle))]
        (cond
          (= fst needle) p
          rst (recur (inc p) rst)
          :else -1))))

(comment
  (strpos "a" "baa")                    ;=> 1
  (strpos "c" "baa")                    ;=> -1
  (strpos "abcd" "abcabcdabcde")        ;=> 3
)
