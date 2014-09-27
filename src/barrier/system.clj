(ns barrier.system
  (:require [com.stuartsierra.component :as component]
            [barrier.webserver :as webserver]))

(def default-webserver-options
  {:ip "0.0.0.0"
   :port 8090
   :threads 4
   :worker-name-prefix "worker-"
   :max-body 8388608
   :max-line 4096})

(defn new-system []
  (component/system-map
   :webserver (webserver/new-webserver default-webserver-options)))
