(ns recipes-app.handler.example
  (:require [ataraxy.core :as ataraxy]
            [ataraxy.response :as response] 
            [clojure.java.io :as io]
            [integrant.core :as ig]))

(defmethod ig/init-key :recipes-app.handler/example [_ options]
  (fn [{[_] :ataraxy/result}]
    [::response/ok (io/resource "recipes_app/handler/example/example.html")]))
