
(ns helsam.layout)

(defn frame [pane]
  [:div.b-page
    [:div.b-top "top" [:br][:br]]
    [:div.b-side-pane
      [:div.b-side
        [:div.b-bar
          "side bar"]]
      [:div.b-pane pane]]
    [:div.b-bottom "bottom"]])
;

;.
