(ns barrier.webserver
  (:require [com.stuartsierra.component :as component]
            [org.httpkit.server :refer [run-server]]))

(defn demo-app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Hello Webserver!!!"})

(defrecord WebServer [ip port threads worker-name-prefix max-body max-line]
  component/Lifecycle
  (start [component]
    (let [options {:ip ip
                   :port port
                   :thread threads
                   :worker-name-prefix worker-name-prefix
                   :max-body max-body
                   :max-line max-line}]
      (println "Starting web server on" (str ip ":" port))
      (assoc component :server (run-server demo-app options))))
  (stop [component]
    (println "Stopping web server")
    (let [stop-server (:server component)]
      (stop-server :timeout 100))
    (dissoc component :server)))

(defn new-webserver [options]
  (map->WebServer options))
