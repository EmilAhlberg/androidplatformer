#Fixlist:#


* Fixa så man inte accelererar när man glider nedåt mot en vägg

* Fixa till gameLoop-tråden??? <--

* GameTime implementerad --> ändra alla counters till gameTime-typ (beroende av tiden, ej random counters)

* Musik och ljud! (yeah)

* Rullande / statisk / annan animerad bakgrund ?






###DONE:###
* Particles framework implementerad!

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

* Konstig respons på touchEvents, får ibland delay? ##### Kan kanske lösas genom att ha en tråd som ritar bitmaps som den lägger i en monitor, ui-tråden behöver sedan bara hämta dessa bitmaps(?)

* Lagg vid switch från gameActivity


##Other fix##