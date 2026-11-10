(ns salsa-app.core
  (:gen-class))

(def salsa-song
  {:title "Vamonos Pa'l Monte"
   :bpm 180
   :first-beats [2.0 4.0 6.0 8.0]
   :break-times [15.5 45.2 78.0]})

(defn check-break
  ([song user-time]
   (check-break song user-time 0.5))
  ([song user-time tolerance]
   (let [{:keys [title break-times]} song]
     (if (some #(<= (Math/abs (- % user-time)) tolerance) break-times)
       (str "Bravo! You hit the break in song: " title)
       (str "Missed! That is not a break in song: " title)))))

(defn check-beat
  ([song user-time]
   (check-beat song user-time 0.3))
  ([song user-time tolerance]
   (let [{:keys [title first-beats]} song]
     (if (some #(<= (Math/abs (- % user-time)) tolerance) first-beats)
       (str "Great timing! You hit beat 1 in: " title)
       (str "Off beat! Try again for: " title)))))

(defn print-song-info
  [song]
  (println "Song title:" (:title song))
  (println "BPM:" (:bpm song)))

(defn -main
  [& args]
  (println "Salsa Musicality Trainer")
  (println "------------------------")

  (print-song-info salsa-song)
  (println)

  (println (check-break salsa-song 15.8))
  (println (check-break salsa-song 20.0))

  (println (check-beat salsa-song 2.1)))