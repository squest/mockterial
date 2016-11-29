(ns alfa.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [alfa.events]
              [alfa.subs]
              [alfa.views :as views]
              [alfa.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
