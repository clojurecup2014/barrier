(ns barrier.app
  (:require [com.stuartsierra.component :as component]
            [spiral.core :as spiral]))

(defn get-ring-handler [component]
  (fn [req] {:status  200
             :headers {"Content-Type" "text/html"}
             :body    "Hello Ring App!!!"}))

(defn get-spiral-handler [component]
  (spiral/constant-response
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body "Hello Spiral App!!!"}))

(defrecord App []
  component/Lifecycle
  (start [component]
    (println "Starting app")
    (assoc component :handler (get-spiral-handler component)))
  (stop [component]
    (println "Stopping app")
    (dissoc component :handler :ring-type)))

(defn new-app [options]
  (map->App options))
