#Fixlist:#

* Fixa så man inte accelererar när man glider nedåt mot en vägg

* GameTime implementerad --> ändra alla counters till gameTime-typ (beroende av tiden, ej random counters)

* Musik och ljud! (yeah)

* Rullande / statisk / annan animerad bakgrund ?

* Dubbelbuffra handlermessages för snyggare uppritning?

* Horizontell - Vertikala blockhörn, kollisionsbugg

* "Sned uppritning", ser ut som flimmer när player rör sig sidledes.





###DONE:###

* Ändra sleep() till wait()

* Particles framework implementerad!

* Fixa uppritningen i ui-tråden, flytta så mycket funktionalitet som möjligt till en "worker-thread"

* Konstig respons på touchEvents, får ibland delay? ##### se fixlist (fixa uppritning i ui), det borde fixa problemet

Generell bigObject-hantering i levelCreator.

Mer generell bigblock/bighazard + effektivare uppritning med större bitmap

Kollisionshanteringen funkar bara ~99.9% av gångerna. Inget allvarligt fel, bara att upplevelsen inte blir lika nice.  //borde fungera 100% nu?





##Refactoring suggestions ##

* Hårdkodning. Förslag: efter varje hårdkodad variabel som tilldelas hittats, markera med typ //!! (enklare att hitta och fixa senare).

* Lösa snyggare infoutbyte mellan levelcreator - world, slippa läcka info med många statiska get-metoder.



##Gameplay suggestions ##
Fler snygga bilder ;)

Enemies

Hazards

Special moves

Special levels (boss / timed levels)

Story! (cut scene /  boss/enemy dialogue)

##Bugs##

* Hantera telefonfunktioner; skärmsläckare / rotationer / home button / return button / etc

* Lagg vid switch från gameActivity


##Other fix##