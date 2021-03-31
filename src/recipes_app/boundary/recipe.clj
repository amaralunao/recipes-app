(ns recipes-app.boundary.recipe
  (:require [clojure.java.jdbc :as jdbc]
            duct.database.sql)
  (:import java.sql.SQLException))

(defprotocol RecipeDatabase
  (list-recipes [db])
  (create-recipe [db recipe]))

(extend-protocol RecipeDatabase
  duct.database.sql.Boundary
  (list-recipes [{db :spec}]
    (jdbc/query db ["SELECT * FROM recipe"]))
  (create-recipe [{db :spec} recipe]
    (try
      (let [result (jdbc/insert! db :recipe recipe)]
        (if-let [id (val (ffirst result))]
          {:id id}
          {:errors ["Failed to add recipe."]}))
      (catch SQLException ex
        {:errors [(format "Recipe not added due to %s" (.getMessage ex))]}))))
