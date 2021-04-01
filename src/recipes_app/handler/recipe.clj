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

(defmethod ig/init-key :recipes-app.handler.recipe/list [_ {:keys [db]}]
  (fn [_]
    (let [recipes-list (boundary.recipe/list-recipes db)]
      (if (seq recipes-list)
        [::response/ok (views.recipe/list-recipes-view recipes-list {})]
        [::response/ok (views.recipe/list-recipes-view [] {:messages ["No recipes found."]})]))))

(defmethod ig/init-key :recipes-app.handler.recipe/get [_ {:keys [db]}]
   (fn [{[_ rid] :ataraxy/result :as request}]
     (let [recipe (into {} (boundary.recipe/get-recipe db rid))]
       (if (not-empty recipe)
         [::response/ok (views.recipe/recipe-view recipe {})]
         [::response/ok (views.recipe/recipe-view [] {:messages ["No recipe found."]})]))))
