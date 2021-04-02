(ns recipes-app.handler.index-test
  (:require [recipes-app.handler.index]
            [clojure.test :refer [deftest testing is]]
            [ring.mock.request :as mock]
            [integrant.core :as ig]))

(deftest check-index-handler
  (testing "Ensure that the index handler returns two links for add and list recipes"
    (let [handler (ig/init-key :recipes-app.handler.index/index {})
          response (handler (mock/request :get "/"))]
      (is (= :ataraxy.response/ok (first response)))
      (is (= "href=\"/add-recipe\""
             (re-find #"href=\"/add-recipe\"" (second response))))
      (is (= "href=\"/recipes\""
             (re-find #"href=\"/recipes\"" (second response)))))))
