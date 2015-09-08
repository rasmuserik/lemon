;; # Lemon
;;
;; This repository will contain widgets and apps for Tinkuy/NewCircleMovement/...
;;
;; # Literate source code

(ns solsort.lemon
  (:require-macros
    [reagent.ratom :as ratom :refer [reaction]]
    [cljs.core.async.macros :refer  [go alt!]])

  (:require
    [cljs.test :refer-macros  [deftest testing is run-tests]]
    [solsort.util :refer [route log unique-id]]
    [re-frame.core :as re-frame :refer [subscribe]]
    [cljs.core.async :refer [>! <! chan put! take! timeout close!]]))

;; ## example/test-app
(route
  "lemon"
  (fn []
    (reaction {:type :app
           :title "lemon"
           :navigate-back {:event ['home] :title "Home" :icon "home"}
           :actions [ {:event [:log "pressed hello"] :icon "hello"}
                     {:event ['paste] :icon "paste"} ]
           :views [ {:event ['view-left] :icon "left"}
                   {:event ['view-right] :icon "right"} ]
           :html
           [:div
            ; show log
            (map (fn [e] [:div {:key (unique-id)} (.slice (str e) 1 -1)]) (reverse @(subscribe [:log])))
            (str (range 1000)) ; some random text that takes space
            
            ]})))
