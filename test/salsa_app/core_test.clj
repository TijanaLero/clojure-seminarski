(ns salsa-app.core-test
  (:require [midje.sweet :refer :all]
            [salsa-app.core :refer :all]))

(facts "Testing break function"
       (fact "when user hit correct break time with default tolerance"
             (check-break salsa-song 15.8) => "Bravo! You hit the break in song: Vamonos Pa'l Monte")

       (fact "when user miss break time"
             (check-break salsa-song 20.0) => "Missed! That is not a break in song: Vamonos Pa'l Monte")

       (fact "when user miss break time with custom tolerance"
             (check-break salsa-song 15.8 0.1) => "Missed! That is not a break in song: Vamonos Pa'l Monte"))

(facts "Testing beat function"
       (fact "when user hit beat 1 correct with default tolerance"
             (check-beat salsa-song 2.1) => "Great timing! You hit beat 1 in: Vamonos Pa'l Monte")

       (fact "when user miss beat 1"
             (check-beat salsa-song 5.0) => "Off beat! Try again for: Vamonos Pa'l Monte")

       (fact "when user miss beat 1 with custom tolerance"
             (check-beat salsa-song 2.1 0.2) => "Great timing! You hit beat 1 in: Vamonos Pa'l Monte"))