(ns helsam.page
  (:require
    [reagent.core :as reagent]
    ;
    [helsam.layout :as layout]
    [helsam.auth0 :refer [auth-btn token-pane]]
    [helsam.patient :as patient]))

;

(enable-console-print!)

(defn log [msg & args]
  (.log js/console (str msg) (clj->js args)))
;

;; window.location.hash
;; http://localhost:3000/#access_token=mlMqeVX_kigOoKD9&expires_in=86400&id_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL21heHAuYXV0aDAuY29tLyIsInN1YiI6ImF1dGgwfDU5MjZiZTJmZDk2NDNmMzIzNWY0ZDc3MCIsImF1ZCI6IjhPN0lwNmlVOTFEbmRXRmNKdlo5RkhQdzhjd3g1RlozIiwiZXhwIjoxNDk1ODMzOTAyLCJpYXQiOjE0OTU3OTc5MDJ9.cZ5LKpr5vX3UFUZsWu0B8L8Fg0S0sVBhLcN_aofVZ2o&token_type=Bearer

;; NOTE: double submit not checked

(defn btn-list []
  [:button.btn.btn-default
    { :type :button
      :on-click
        (fn [evt]           ;; NOTE: preventDefault
          #(log "btn-list click:" evt)
          (patient/get-list))}
    "list all"])
;

(defn btn-create []
  [:button.btn.btn-default
    { :type :button
      :on-click
        (fn [evt]      ;; NOTE: preventDefault
          #(log "btn-create click:" %)
          (patient/create-patient patient/PAT1))}
    "create"])
;

(defn ui []
  [:div
    [:h2 "content pane"]
    [:div.col-sm-6
      [auth-btn]
      [token-pane]
      [:hr]
      [btn-list]
      " "
      [btn-create]]
    [:div.clearfix]
    [:hr]
    [:div.col-sm-6 "col-1"]
    [:div.col-sm-6 "col-2"]])
;


(.log js/console "start")


(defn ^:export run []
  (.log js/console "run.")
  (reagent/render
    [layout/frame [ui]]
    (js/document.getElementById "app")))
;

;;.
