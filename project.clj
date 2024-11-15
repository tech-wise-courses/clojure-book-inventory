(defproject book-inventory "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [cheshire "5.10.1"]]
  :source-paths ["src"]
  :main book-inventory.core
  :aot [book-inventory.core]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
