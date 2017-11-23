#Fixlist:#


* Fixa så man inte accelererar när man glider nedåt mot en vägg

* Particles class!

* Fixa till gameLoop-tråden?

* Skapa en klass GameTime, vilken synkar alla updates? (updateras i gameLoop, parameter ned till alla klasser via world.update(gameTime); ) 

* Musik och ljud! (yeah)

* Rullande / statisk / annan animerad bakgrund ?






###DONE:###
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

* Konstig respons på touchEvents, får ibland delay?

* Lagg vid switch från gameActivity


##Other fix##