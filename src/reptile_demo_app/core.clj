(ns reptile-demo-app.core
  (:require [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.core.server :as clj-server])
  (:import [java.net ServerSocket]
           [clojure.lang DynamicClassLoader]))

(defn time-str
  "Returns a string representation of a datetime in the local time zone."
  [dt]
  (f/unparse
    (f/with-zone (f/formatter "hh:mm aa") (t/default-time-zone))
    dt))

(defn time-now
  "Sample call - maybe from a connected REPL"
  [who]
  (str "We have the time " (time-str (t/now)) ", for you: " who))

(defn shared-prepl
  [opts]
  (let [socket-opts {:port          0
                     :server-daemon false                   ; Keep the app running
                     :accept        'clojure.core.server/io-prepl}]

    ;; A clojure.lang.DynamicClassLoader is needed to enable interactive library addition
    (try (let [cl (.getContextClassLoader (Thread/currentThread))]
           (.setContextClassLoader (Thread/currentThread) (DynamicClassLoader. cl))

           (let [server (clj-server/start-server (merge socket-opts opts))]
             (println "listening on port" (.getLocalPort ^ServerSocket server))))

         (catch Exception e (str "ClassLoader issue - caught exception: " (.getMessage e))))))

(defn -main
  [& args]
  (let [port (if (> (count args) 0) (Integer/parseInt (first args)))]
    (shared-prepl {:name "demo-time-app" :port (or port 0)})))


