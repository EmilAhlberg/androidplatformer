#Fixlist:#


* Fixa så man inte accelererar när man glider nedåt mot en vägg

* Generell bigObject-hantering i levelCreator. Finns stöd för block läggs ihop, fire behöver dock "räknas", nice om det går med generell lösning för framtida bigObjects.

* Fixa till gameLoop-tråden

* Konstig respons på touchEvents, får ibland delay?



###DONE:###
Mer generell bigblock/bighazard + effektivare uppritning med större bitmap

Kollisionshanteringen funkar bara ~99.9% av gångerna. Inget allvarligt fel, bara att upplevelsen inte blir lika nice.  //borde fungera 100% nu?





##Refactoring suggestions ##

* Hårdkodning. Förslag: efter varje hårdkodad variabel som tilldelas hittats, markera med typ //!! (enklare att hitta och fixa senare).

* Lösa snyggare infoutbyte mellan levelcreator - world, slippa läcka info med många statiska get-metoder.



##Gameplay suggestions ##
Fler snygga bilder ;)

Enemies

Hazards

##Bugs##



##Other fix##