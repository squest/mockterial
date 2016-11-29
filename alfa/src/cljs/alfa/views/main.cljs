(ns alfa.views.main
  (:require [re-frame.core :as re]))

(defn main-panel []
  (let [name (re/subscribe [:name])]
    (fn []
      [:div "Hello from " @name])))

