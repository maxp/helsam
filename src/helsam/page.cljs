(ns helsam.page
  (:require
    [reagent.core :as reagent]
    ;
    [helsam.layout :as layout]
    [helsam.patient :as patient]))
;

(enable-console-print!)

(defn log [msg & args]
  (.log js/console (str msg) (clj->js args)))
;


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
