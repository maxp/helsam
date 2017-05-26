
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

(defn query-params [h]
  (->
    (s/split (str h) #"&")
    (map #(s/split % #"="))
    (into {})
    (not-empty)))
;

(defn decode-b64 [s]
  (when s
    (->> s
      (decodeString)
      (.parse js/JSON)
      (js->clj))))
;


(defn get-id-token [h]
  (prn "h:" h)
  (when-let [idt (get (query-params h) "id_token")]
    (prn "id_tok:" idt)
    (let [[hdr data sign] (s/split idt #"\.")]
      (prn "id_tok:" idt)
      (decode-b64 data))))
;

(def id_token
  (-> js/window .-location .-hash get-id-token))
;

(prn "hash:"
  (-> js/window .-location .-hash get-id-token))

(defn auth-btn []
  [:a.btn.btn-link
    {:href AUTH_URL}
    "auth0 login"])
;

(defn token-pane []
  [:div
    ""
    (str id_token)])
;

;;.
