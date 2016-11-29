(ns alfa.core
  (:require
    [reagent.core :as rc]
    [re-frame.core :as re]
    [alfa.events]
    [alfa.subs]
    [alfa.views.main :as views]))


(defn mount-root []
  (rc/render [views/main-panel]
             (.getElementById js/document "app")))

(defn ^:export init []
  (re/dispatch-sync [:initialize-db])
  (mount-root))
