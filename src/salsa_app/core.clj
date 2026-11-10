(ns salsa-app.core
  (:gen-class))

(def salsa-songs
  [{:title "Vamonos Pa'l Monte"
    :bpm 180
    :first-beats [2.0 4.0 6.0 8.0]
    :break-times [15.5 45.2 78.0]}
   {:title "Todo Una Vida"
    :bpm 160
    :first-beats [1.5 3.5 5.5 7.5]
    :break-times [30.0 62.5 95.0]}
   {:title "Vivir Mi Vida"
    :bpm 200
    :first-beats [1.8 3.8 5.8 7.8]
    :break-times [25.0 50.0 80.0]}])

(def salsa-song (first salsa-songs))

(defn fast-songs
  [songs min-bpm]
  (filter #(> (:bpm %) min-bpm) songs))

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

  (println (check-beat salsa-song 2.1))

  (println "\nFast songs for practice (>170 BPM):")
  (println (map :title (fast-songs salsa-songs 170))))