(ns recipes-app.views.index
  (:require [recipes-app.views.template :refer [page]]))

(defn list-options []
  (page
   [:div.container.jumbotron.bg-white.text-center
    [:row
     [:p
      [:a.btn.btn-primary {:href "/add-recipe"} "Add a Recipe"]]]
    [:row
     [:p
      [:a.btn.btn-primary {:href "/recipes"} "List Recipes"]]]]))
