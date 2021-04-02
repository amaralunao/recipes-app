(ns recipes-app.views.recipe
  (:require [recipes-app.views.template :refer [page labeled-radio]]
            [hiccup.form :refer [form-to label text-field text-area submit-button]]
            [ring.util.anti-forgery :refer [anti-forgery-field]]))

(defn create-recipe-view
  []
  (page
   [:div.container.jumbotron.bg-light
    [:div.row
     [:h2 "Add a recipe"]]
    [:div
     (form-to [:post "/add-recipe"]
              (anti-forgery-field)
              [:div.form-group.col-12
               (label :name "Name:")
               (text-field {:class "mb-3 form-control" :placeholder "Enter recipe name"} :name)]
              [:div.form-group.col-12
              (label :description "Author:")
               (text-area {:class "mb-3 form-control" :placeholder "Enter recipe Author"} :author)]
              [:div.form-group.col-12
               (label :description "Url:")
               (text-area {:class "mb-3 form-control" :placeholder "Enter recipe url"} :url)]
              [:div.form-group.col-12
               (label :description "Text:")
               (text-area {:class "mb-3 form-control" :placeholder "Enter recipe text"} :text)]
              [:div.form-group.col-12
               (label :ratings "Rating (1-10):")]
              [:div.form-group.btn-group.col-12
               (map (labeled-radio "rating") (repeat 10 false) (range 1 11))]
              [:div.form-group.col-12.text-center
               (submit-button {:class "btn btn-primary text-center"} "Add")])]]))

(defn- recipe-attributes-view
  [id name author url text rating]
  [:div
   (when id
     [:div.row
      [:div.col-2 "Id:"]
      [:div.col-10 id]])
   (when name
     [:div.row
      [:div.col-2 "Name:"]
      [:div.col-10 name]])
   (when author
     [:div.row
      [:div.col-2 "Author:"]
       [:div.col-10 author]])
   (when url
     [:div.row
      [:div.col-2 "Url:"]
      [:div.col-10 url]])
   (when text
     [:div.row
      [:div.col-2 "Text:"]
      [:div.col-10 text]])
   (when rating
     [:div.row
      [:div.col-2 "Rating:"]
      [:div.col-10 rating]])])

(defn recipe-view
  [{:keys [id name author url text rating]} {:keys [errors messages]}]
  (page
   [:div.container.jumbotron.bg-light
    [:div.row
     [:h2 "Recipe"]]
    (recipe-attributes-view id name author url text rating)
    (when errors
      (for [error (doall errors)]
       [:div.row.alert.alert-danger
        [:div.col error]]))
    (when messages
      (for [message (doall messages)]
       [:div.row.alert.alert-success
        [:div.col message]]))]))


(defn list-recipes-view
   [recipes {:keys [messages]}]
   (page
    [:div.container.jumbotron.bg-light
     [:div.row [:h2 "Recipes"]]
     (for [{:keys [id name author url text rating]} (doall recipes)]
       [:div
        (recipe-attributes-view id name author url text rating)
        [:hr]])
     (when messages
       (for [message (doall messages)]
         [:div.row.alert.alert-success
          [:div.col message]]))]))
