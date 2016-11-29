(ns alfa.views.main
  (:require-macros [reagent.interop :refer [$! $]])
  (:require
    [cljs-react-material-ui.core :as ui]
    [cljs-react-material-ui.icons :as ic]
    [cljs-react-material-ui.reagent :as rui]
    [alfa.utils :as u :refer [$eval]]
    [reagent.core :as rc]
    [re-frame.core :as re]
    [alfa.themes :refer [zenius-theme]]))

(defn main-panel []
  (let [name (re/subscribe [:name])]
    (fn []
      [:center
       [rui/app-bar
        {:title "Zenius Prestasi"}]])))

(defn main-page
  []
  [rui/mui-theme-provider
   {:mui-theme (ui/get-mui-theme zenius-theme)}
   [main-panel]])

