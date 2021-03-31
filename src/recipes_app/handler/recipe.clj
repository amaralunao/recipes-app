(ns recipes-app.handler.recipe
  (:require [ataraxy.core :as ataraxy]
            [ataraxy.response :as response]
            [recipes-app.boundary.recipe :as boundary.recipe]
            [recipes-app.views.recipe :as views.recipe]
            [integrant.core :as ig]))

(defmethod ig/init-key :recipes-app.handler.recipe/show-create [_ _]
  (fn [_]
    [::response/ok (views.recipe/create-recipe-view)]))

(defmethod ig/init-key :recipes-app.handler.recipe/create [_ {:keys [db]}]
  (fn [{[_ recipe-form] :ataraxy/result :as request}]
    (let [recipe (reduce-kv (fn [m k v] (assoc m (keyword k) v))
                          {}
                          (dissoc recipe-form "__anti-forgery-token"))
          result (boundary.recipe/create-recipe db recipe)
          alerts (if (:id result)
                   {:messages ["Recipe added"]}
                   result)]
      [::response/ok (views.recipe/recipe-view recipe alerts)])))
