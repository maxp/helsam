
(ns fv.layout)

(defn page [pane]
  [:div.b-page
    [:div.b-top "top" [:br] "top menu"]
    [:div.b-side-pane
      [:div.b-side
        [:div.b-bar
          "side bar"]]
      [:div.b-pane "pane" pane]]
    [:div.b-bottom "bottom"]])
;
