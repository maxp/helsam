; (ns fv.core
;   (:require
;     [reagent.core :as reagent]
;     [re-frame.core :as rf]
;     ;
;     [fv.layout :as layout]))
; ;
;
; (enable-console-print!)
;
; (defn dispatch-timer-event
;   []
;   (let [now (js/Date.)]
;     (rf/dispatch [:timer now])))
;
;
; (defonce do-timer (js/setInterval dispatch-timer-event 5000))
;
;
; ;; usage:  (dispatch [:initialize])
; (rf/reg-event-db
;   :initialize
;   (fn [_ _]
;     {:time (js/Date.)  :time-color "#f88"}))
;
;
; ;; usage:  (dispatch [:time-color-change 34562])
; (rf/reg-event-db
;   :time-color-change
;   (fn [db [_ new-color-value]]
;     (assoc db :time-color new-color-value)))
;
;
; ;; usage:  (dispatch [:timer a-js-Date])
; (rf/reg-event-db
;   :timer
;   (fn [db [_ new-time]]
;     (assoc db :time new-time)))
;
;
;
; (rf/reg-sub
;   :time
;   (fn [db _]
;     (.log js/console ":time - " _)
;     (-> db
;         :time)))
;
; (rf/reg-sub
;   :time-color
;   (fn [db _]
;     (:time-color db)))
;
;
;
; (defn clock
;   []
;   [:div.example-clock
;    {:style {:color @(rf/subscribe [:time-color])}}
;    (-> @(rf/subscribe [:time])
;        .toTimeString
;        (clojure.string/split " ")
;        first)])
; ;
;
;
; (defn color-input
;   []
;   [:div.color-input
;    "Time color: "
;    [:input {:type "text"
;             :value @(rf/subscribe [:time-color])
;             :on-change #(rf/dispatch [:time-color-change (-> % .-target .-value)])}]])  ;; <---
; ;
;
; (defn ui
;   []
;   [:div
;    [:h1 "Hello world, it is now"]
;    [clock]
;    [color-input]])
; ;
;
;
; (.log js/console "start")
;
;
; (defn ^:export run []
;   (.log js/console "run.")
;   (rf/dispatch-sync [:initialize])
;   (reagent/render
;     [layout/page [ui]]
;     (js/document.getElementById "app")))
; ;
;
; ;;.
