#Fixlist:#


* Fixa s� man inte accelererar n�r man glider ned�t mot en v�gg

* Fixa tr�ds�kerhet (i synnerhet med gametime/pause i gameLoop, b�st att inte extenda Thread)

* Fixa till gameLoop-tr�den??? <--

* GameTime implementerad --> �ndra alla counters till gameTime-typ (beroende av tiden, ej random counters)

* Musik och ljud! (yeah)

* Rullande / statisk / annan animerad bakgrund ?






###DONE:###
* Particles framework implementerad!

Generell bigObject-hantering i levelCreator.

Mer generell bigblock/bighazard + effektivare uppritning med st�rre bitmap

Kollisionshanteringen funkar bara ~99.9% av g�ngerna. Inget allvarligt fel, bara att upplevelsen inte blir lika nice.  //borde fungera 100% nu?





##Refactoring suggestions ##

* H�rdkodning. F�rslag: efter varje h�rdkodad variabel som tilldelas hittats, markera med typ //!! (enklare att hitta och fixa senare).

* L�sa snyggare infoutbyte mellan levelcreator - world, slippa l�cka info med m�nga statiska get-metoder.



##Gameplay suggestions ##
Fler snygga bilder ;)

Enemies

Hazards

Special moves

Special levels (boss / timed levels)

Story! (cut scene /  boss/enemy dialogue)

##Bugs##

* Hantera telefonfunktioner; sk�rmsl�ckare / rotationer / home button / return button / etc

* Konstig respons p� touchEvents, f�r ibland delay? ##### skippar fler och fler frames ju l�ngre appen k�rs, information overload n�gonstans? Har vi cappad fps? om inte kanske det �r det som g�r touchen unresponsive (m�nga updateScreen-calls i handler k�n?)

* Lagg vid switch fr�n gameActivity


##Other fix##