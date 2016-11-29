(ns alfa.events
  (:require [re-frame.core :as re]
            [alfa.db :as db]))

(re/reg-event-db
  :initialize-db
  (fn  [_ _]
    db/default-db))
