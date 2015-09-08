;; # Lemon
;;
;; This repository will contain widgets and apps for Tinkuy/NewCircleMovement/...
;;
;; It is currently in very initial development, and not really usable for anything yet.
;; More info will follow later.
;;
;; ## Development environment / Getting started
;;
;; Install leiningen, and:
;;
;;     lein figwheel
;; 
;; Then a local development-version of the NewCircleMovement/tinkuy ruby app, 
;; running on port 3000, will connect directly to the clojurescript environment,
;; with repl-support with the tinkuy-site.
;;
;; # Literate source code
;;
;; I like the concept of 
;; [literate programming](https://en.wikipedia.org/wiki/Literate_programming),
;; where the code is written as a document to be read by humans too.
;; In the following there will be the actual code, intermixed with a description
;; of the ideas behind it.
;;
;; ## Namespace definition
;;
;; Define the module, and declare the dependencies. Use the standard ClojureScript modules
(ns solsort.lemon
  (:require-macros
    [reagent.ratom :as ratom :refer [reaction]]
    [cljs.core.async.macros :refer  [go alt!]])
  (:require
    [cljs.test :refer-macros  [deftest testing is run-tests]]
    [cljs.core.async :refer [>! <! chan put! take! timeout close!]]

;; It uses the re-frame framework.
;;
;; If you are interested in client-side development in general, 
;; read the [re-frame](https://github.com/Day8/re-frame) readme.
;; As that is a very good document about how to structure application.
    [re-frame.core :as re-frame :refer [subscribe]]

;; And some of my own utility functions, that I share among projects.
;; Routing, platform-abstraction, utilities, etc.
    [solsort.util :refer [route log unique-id]]
    ))

;; ## Sample/getting started code
;;
;; This is just a small hello-world app, will be replaced by the actual code soon.
(route
  "lemon" :app
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
