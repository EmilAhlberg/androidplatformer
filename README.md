#Fixlist:#


* Fixa s� man inte accelererar n�r man glider ned�t mot en v�gg

* Fixa till busy wait i GameLoop-klassen, det ligger en todo i koden d�r den �r

* Fixa uppritningen i ui-tr�den, flytta s� mycket funktionalitet som m�jligt till en "worker-thread"

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

* Konstig respons p� touchEvents, f�r ibland delay? ##### se fixlist (fixa uppritning i ui), det borde fixa problemet

* Lagg vid switch fr�n gameActivity


##Other fix##