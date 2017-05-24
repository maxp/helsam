
(ns helsam.patient
  (:require
    [ajax.core :refer [GET POST]]))
;

(def URL "https://helsam.aidbox.io/fhir")

(defn log [msg & args]
  (.log js/console (str msg) (clj->js args)))
;



(defn handler [response]
  (log "handler:" response))

(defn error-handler [{:keys [status status-text]}]
  (log "errh:" status status-text))



(defn get-list []
  (let [url (str URL "/Patient")]
    (GET url
      {
        :format :json
        :response-format :json
        :keywords? true
        :error-handler error-handler
        :handler
          (fn [resp]
            (doseq [p (:entry resp)]
              (prn "p:" p)))})))
;


(defn create-patient [data]
  (let [url (str URL "/Patient")]
    (POST url
      {
        :format :json
        :response-format :json
        :params data})))
;


(def PAT1
  {
    :name
      [
        { :use "official"
          :given ["Adam" "Testpatient"]
          :family ["Hanson"]
          :suffix ["Sr"]}
        { :use "usual"
          :given ["Test Patient"]}]
    :birthDate "1942-10-06"
    :resourceType "Patient"})
;


;;.
