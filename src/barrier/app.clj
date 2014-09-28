(ns barrier.app
  (:require
    [barrier.backends :as backends]
    [com.stuartsierra.component :as component]
    [compojure.core :refer (defroutes GET ANY)]
    [spiral.core :as spiral]
    [spiral.beauty :refer (beauty-router beauty-route)]))

(def default-pools
  {:main {:parallelism 1}
   :endpoint {:parallelism 5
              :buffer-size 100}})

(defroutes barrier-routes
  (GET "/" [] (beauty-route :main {:status 200
                                   :headers {"Content-Type" "text/html"}
                                   :body "Hello Barrier!!!"})))

(defn create-beauty-router [component]
  (beauty-router barrier-routes default-pools))

(defrecord App [backends]
  component/Lifecycle
  (start [component]
    (println "Starting app")
    (assoc component :handler (create-beauty-router component)))
  (stop [component]
    (println "Stopping app")
    (dissoc component :handler :ring-type)))

(defn new-app [options]
  (map->App options))
