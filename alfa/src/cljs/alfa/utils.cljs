(ns alfa.utils)

(defn $eval [obj]
  (-> obj .-target .-value))
