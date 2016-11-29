(ns user
  (:require
    [com.stuartsierra.component :as component]
    [noir.session :as sess]
    [immutant.web :as web]
    [immutant.util :as log]
    [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
    [selmer.parser :as selmer :refer [render-file]]
    [compojure.core :refer [GET POST context routes]]
    [compojure.route :refer [resources files not-found]]
    [ring.util.response :as resp]))

(defn home []
  (render-file "template/index.html" {}))

(defn main-routes
  []
  (routes
    (GET "/" req (home))
    (resources "/")
    (not-found "<center><h1>Nothing to see here</h1></center>")))

(defrecord Handler []
  component/Lifecycle
  (start [this]
    (assoc this :routes (main-routes)))
  (stop [this] this))

(defn make-handler []
  (map->Handler {}))

(defrecord Server [port handler path host]
  component/Lifecycle
  (start [this]
    (println "Starting the server... A Jew, an Arab, and Donald Trump walk into a bar...")
    (let [site-config (assoc-in site-defaults [:security :anti-forgery] false)
          site (-> (:routes handler)
                   (wrap-defaults site-config))]
      (log/set-log-level! :OFF)
      (do (println "")
          (println "Server has started")
          (println "Now you can open your browser on http://localhost:5000\n"))
      (assoc this :stop-fn (web/run site {:port port :path path :host host}))))
  (stop [this]
    (println "Stopping the server... ")
    (web/stop (:stop-fn this))
    (dissoc this :stop-fn)))

(defn make-server [server-config]
  (map->Server server-config))

(defonce system (atom nil))

(defn create-system []
  (component/system-map
    :server (-> (make-server {:port 5000 :path "/" :host "localhost"})
                (component/using [:handler]))
    :handler (make-handler)))

(defn start []
  (reset! system (create-system))
  (swap! system component/start-system))

(defn stop []
  (swap! system component/stop-system))

(defn restart []
  (stop)
  (start))



