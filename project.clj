(defproject barrier "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.stuartsierra/component "0.2.1"]
                 [compojure "1.1.9"]
                 [http-kit "2.1.16"]
                 [prismatic/schema "0.3.0"]

                 ;; spiral dependencies
                 [org.clojure/tools.logging "0.2.6"]
                 [org.clojure/data.priority-map "0.0.5"]
                 [ring "1.2.2"]
                 [org.clojure/core.async "0.1.303.0-886421-alpha"]
                 ]
  :source-paths ["src" ".lein-git-deps/spiral/src"]
  :plugins [[lein-git-deps "0.0.1-SNAPSHOT"]]
  :git-dependencies [["https://github.com/dgrnbrg/spiral.git"]]
  :profiles {:dev {:dependencies [[org.clojure/tools.namespace "0.2.4"]]
                   :source-paths ["dev"]}})
