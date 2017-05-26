
(ns helsam.auth0
  (:require
    [clojure.string :as s]
    [goog.crypt.base64 :refer [decodeString]]
    [ajax.core :refer [GET POST]]))
;

(defn log [msg & args]
  (.log js/console (str msg) (clj->js args)))
;

;; l/p: test1 qwe123

(def CALLBACK_URL
  "http://localhost:3000/")

(def AUTH_URL
  (str
    "https://maxp.auth0.com/authorize"
    "?client_id=8O7Ip6iU91DndWFcJvZ9FHPw8cwx5FZ3"
    "&response_type=token"
    "&redirect_uri=" CALLBACK_URL))


; response_type=code|token&
; connection=CONNECTION&
; state=STATE&
; additional-parameter=ADDITIONAL_PARAMETERS)

(defn query-params-map [h]
  (when-let [ps (->> (s/split (str h) #"&")
                  (remove s/blank?)
                  (not-empty))]
    (into {} (map #(s/split % #"=") ps))))
;

(defn decode-b64 [s]
  (when s
    (->> s
      (decodeString)
      (.parse js/JSON)
      (js->clj))))
;

(defn get-token-data []
  (let [h (-> js/window .-location .-hash (.substring 1))]
    (when-let [idt (get (query-params-map h) "id_token")]
      (let [[hdr data sign] (s/split idt #"\.")]
        (decode-b64 data)))))
        ;; NOTE: keywordize?
;

(def *token-data (atom nil))

;; NOTE:  future-like, no race state here
(defn token-data! []
  (or
    @*token-data
    (swap! *token-data
      (fn [_] (get-token-data)))))
;

(defn auth-btn []
  [:a.btn.btn-link
    {:href AUTH_URL}
    "auth0 login"])
;

(defn token-pane []
  (when-let [t (token-data!)]
    [:div
      "token-data:"
      [:br]
      (str t)]))

;

;;.
