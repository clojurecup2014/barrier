(ns barrier.backends
  "Configured API backends."
  (:require
    [com.stuartsierra.component :as component]
    [schema.core :as s]))

(defonce backends
  (atom [{:name "Test Backend"
          :base-uri "https://google.com"
          :paths [{:path-prefix #"/google"
                   :backend-path-prefix "/"}]
          :pool {:name :main
                 :parallelism 1
                 :buffer-size 10}}]))

(def PoolSchema
  "A schema for a prioritized, concurrent pool."
  {:name s/Keyword
   :parallelism s/Int
   :buffer-size s/Int})

(def PathSchema
  "A schema for the recognition paths that can be assigned to a backend."
  {:path-prefix (s/either s/Str java.util.regex.Pattern)
   :backend-path-prefix s/Str})

(def BackendSchema
  "A schema for an API backend."
  {:name s/Str
   :base-uri s/Str
   :paths [PathSchema]
   :pool PoolSchema})

(defrecord Backends []
  component/Lifecycle
  (start [component]
    (println "Starting Backends component")
    (assoc component :backends @backends))
  (stop [component]
    (println "Stopping Backends component")
    (dissoc component :backends)))

(defn new-backends [options]
  (map->Backends options))
