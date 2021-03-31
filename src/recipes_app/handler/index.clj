(ns recipes-app.handler.index
  (:require [ataraxy.core :as ataraxy]
            [ataraxy.response :as response]
            [recipes-app.views.index :as views.index]
            [integrant.core :as ig]))

(defmethod ig/init-key :recipes-app.handler/index [_ options]
  (fn [{[_] :ataraxy/result}]
    [::response/ok (views.index/list-options)]))

