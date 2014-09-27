(ns barrier.webserver
  (:require [com.stuartsierra.component :as component]
            [org.httpkit.server :refer [run-server]]
            [spiral.adapters.http-kit :refer [to-httpkit]]))

(defrecord WebServer [ip port threads worker-name-prefix max-body max-line app]
  component/Lifecycle
  (start [component]
    (let [handler (:handler app)
          options {:ip ip
                   :port port
                   :thread threads
                   :worker-name-prefix worker-name-prefix
                   :max-body max-body
                   :max-line max-line}]
      (println "Starting web server on" (str ip ":" port))
      (println "Starting web server with app" app)
      (assoc component :server (run-server (to-httpkit handler) options)
                       :engine "org.httpkit.server")))
  (stop [component]
    (println "Stopping web server")
    (let [stop-server (:server component)]
      (stop-server :timeout 100))
    (dissoc component :server)))

(defn new-webserver [options]
  (map->WebServer options))
