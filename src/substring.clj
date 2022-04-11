(ns substring
  (:require [clojure.string :as str]
            [clojure.spec.alpha :as spec]))

(defn
  ^:private
  substring
  [^String text start len]
  (let [c (.toCharArray text)]
    (str/join (map #(nth c %) (range start (+ start len))))))

(defn
  ^:private
  slider
  ([^String text window-len] (slider text window-len 0))
  ([^String text window-len cur]
   (lazy-seq
    (when (<= (+ cur window-len) (.length text))
      (cons (substring text cur window-len)
            (slider text window-len (inc cur)))))))

(spec/def :substring/find-args (spec/cat :needle string? :haystack string?))

(spec/fdef strpos
  :args :substring/find-args
  :fn #(let [^Long   r (:ret %)
             ^String n (-> % :args :haystack)]
         (and (<= r (.length n)) (or (>= r 0) (= r -1))))
  :ret int?)

(defn ^Long strpos
  "Search for needle as a substring of haystack.
  Return the first position found or -1 if not present."
  [^String needle ^String haystack]
  (letfn)
  (let [s (slider haystack (.length needle))]
    (-> (keep-indexed
         (fn [idx item] (if (= item needle) idx)) s)
        (first)
        (or -1))))

(comment
  (strpos "a" "baa")                    ;=> 1
  (strpos "c" "baa")                    ;=> -1
  (strpos "abcd" "abcabcdabcde")        ;=> 3
  )
