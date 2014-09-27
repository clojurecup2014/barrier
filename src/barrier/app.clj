(ns barrier.app
  (:require [com.stuartsierra.component :as component]))

(defn get-handler [component]
  (fn [req] {:status  200
             :headers {"Content-Type" "text/html"}
             :body    "Hello Webserver App!!!"}))

(defrecord App []
  component/Lifecycle
  (start [component]
    (println "Starting app")
    (assoc component :handler (get-handler component)))
  (stop [component]
    (println "Stopping app")
    (dissoc component :handler)))

(defn new-app [options]
  (map->App options))
