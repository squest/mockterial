(ns alfa.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re]))

(re/reg-sub
 :name
 (fn [db] (:name db)))
