(ns barrier.system
  (:require [com.stuartsierra.component :as component]
            [barrier.app :as app]
            [barrier.webserver :as webserver]))

(def app-config {})

(def webserver-config
  {:ip "0.0.0.0"
   :port 8090
   :threads 4
   :worker-name-prefix "worker-"
   :max-body 8388608
   :max-line 4096})

(defn new-system []
  (component/system-map
    :app (app/new-app app-config)
    :webserver (component/using
                 (webserver/new-webserver webserver-config)
                 [:app])))
