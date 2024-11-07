# Programsko inženjerstvo


# Opis projekta
Ovaj projekt je rezultat timskog rada u sklopu projeknog zadatka kolegija [Programsko inženjerstvo](https://www.fer.unizg.hr/predmet/proinz) na Fakultetu elektrotehnike i računarstva Sveučilišta u Zagrebu. 

Cilj ovog studentskog timskog projekta je osmisliti i razviti aplikaciju koja omogućava brzu reakciju i učinkovitiju koordinaciju između građana, vlasti i humanitarnih organizacija. Rješavamo problem spore i loše komunikacije između vlasti i javnosti u kriznim situacijama. Želimo olakšati dobivanje pomoći i relevantnih informacija unesrećnima. 

Ovim projektom želimo usavršiti svoja znanja i vještine izrade programske potpore, sposobnosti rada u timu i kolegijalnost. 


# Funkcijski zahtjevi
FUNKCIJSKI ZAHTJEVI APLIKACIJE
1.	Aplikacija mora omogućiti lociranje prijava na interaktivnoj mapi (OpenStreetMap)
2.	Aplikacija mora omogućiti registraciju i login korisnika (autentifikacija pomoću OAuth 2.0)

**GRAĐANI**

3.	Aplikacija građanima omogućava prijavu informacija o prirodnim nepogodama
   - 	Vrsta nepogode
   - 	Lokacija nepogode
   - 	Kratki opis situacije
   - 	Fotografije – opcionalno
   - 	Geografske koordinate -  opcionalno

4.	Aplikacija građanima omogućuje praćenje statusa njihovih prijava
    4.1.	 Promjena statusa može se dojaviti putem e-maila ili push notifikacija (FCM ili Twillio)

5.	Aplikacija građanima omogućuje da dobivaju obavijesti o novim prijavljenim nepogodama putem e-maila ili push notifikacija (FCM ili Twillio)

    5.1.	 Obavijesti temeljem lokacija odabranih iz ponuđene liste
    5.2.	 Obavijesti temeljem trenutne lokacije korisnika

6.	Aplikacija građanima omogućuje pristup važnim informacijama:

    6.1.	 O sigurnosnim mjerama

  	 6.2. O najbližim skloništima

  	 6.3.	 O resursima u blizini

7.	Aplikacija omogućava slanje anonimne prijave (bez registracije korisnika)

    7.1.	 Status anonimne prijave može se pratiti putem jedinstvenog identifikacijskog broja

8.	Aplikacija omogućava brz pronalazak uputa za pripremu i reakciju u slučaju ekstremnih prirodnih nepogoda u skladu s trenutnim normama

9.	Aplikacija građanima omogućava prijavu svojih potreba
   - 	Za hranom
   - 	Za smještajem
   - 	Za medicinskom pomoći

**VLASTI**

10.	Aplikacija vlastima omogućava pristup svim prijavama

11.	Aplikacija omogućava vlastima generiranje statističkih izvještaja
   - 	Broj prijava
   - 	Vrste nepogoda
   - 	Učinkovitost odgovora na krize
    
**HUMANITARNE ORGANIZACIJE**

12.	Aplikacija humanitarnim organizacijama omogućava pregled o potrebama građana
   - 	Potreba za smještajem
   - 	Potreba za hranom
   - 	Potreba za medicinskom pomoći

13.	Aplikacija omogućuje dodavanje informacija o dostupnim resursima

14.	Aplikacija omogućuje dodavanje informacija o akcijama pomoći

**ADMINISTRATOR**

15.	Aplikacija administratorima omogućava pregled svih prijava

16.	Aplikacija administratorima omogućava promjenu statusa prijave
   - 	Odobravanje prijava
   - 	Odbijanje prijava

17.	Aplikacija administratorima omogućava povezivanje sličnih prijava

18.	Aplikacija omogućava predlaganje dodatnih resursa

19.	Aplikacija omogućava predlaganje dodatnih mjera

NEFUNKCIJSKI ZAHTJEVI
1.	Aplikacija slijedi Opću uredbu o zaštiti osobnih podataka
2.	Aplikacija mora biti responzivna na različitim uređajima
3.	Aplikacija mora biti usklađena sa standardima pristupačnosti



# Tehnologije

Za frontend ćemo koristiti React.js, a za backend Spring Boot. Deployment ćemo raditi u Renderu. 

Baza podataka - postgreSQL
Komunikacija - Discord
Dizajn - Figma
UML - Astah
Testiranje - Postman

# Članovi tima 
> Elma Vuran - voditelj/backend
> Lana Zekušić - frontend
> Ivan Pastorčić - backend, baza
> Natali Žegarac - backend
> Silvija Rade - frontend, dizajn, dokumentacija
> Krešimir Horvat - frontend
> Iva Sulić - frontend
> 

# Kontribucije
>Pravila ovise o organizaciji tima i su često izdvojena u CONTRIBUTING.md



# 📝 Kodeks ponašanja [![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](CODE_OF_CONDUCT.md)
Kao studenti sigurno ste upoznati s minimumom prihvatljivog ponašanja definiran u [KODEKS PONAŠANJA STUDENATA FAKULTETA ELEKTROTEHNIKE I RAČUNARSTVA SVEUČILIŠTA U ZAGREBU](https://www.fer.hr/_download/repository/Kodeks_ponasanja_studenata_FER-a_procisceni_tekst_2016%5B1%5D.pdf), te dodatnim naputcima za timski rad na predmetu [Programsko inženjerstvo](https://wwww.fer.hr).
Očekujemo da ćete poštovati [etički kodeks IEEE-a](https://www.ieee.org/about/corporate/governance/p7-8.html) koji ima važnu obrazovnu funkciju sa svrhom postavljanja najviših standarda integriteta, odgovornog ponašanja i etičkog ponašanja u profesionalnim aktivnosti. Time profesionalna zajednica programskih inženjera definira opća načela koja definiranju  moralni karakter, donošenje važnih poslovnih odluka i uspostavljanje jasnih moralnih očekivanja za sve pripadnike zajenice.

Kodeks ponašanja skup je provedivih pravila koja služe za jasnu komunikaciju očekivanja i zahtjeva za rad zajednice/tima. Njime se jasno definiraju obaveze, prava, neprihvatljiva ponašanja te  odgovarajuće posljedice (za razliku od etičkog kodeksa).

# 📝 Licenca
Važeča (1)
[![CC BY-NC-SA 4.0][cc-by-nc-sa-shield]][cc-by-nc-sa]

Ovaj repozitorij sadrži otvoreni obrazovni sadržaji (eng. Open Educational Resources)  i licenciran je prema pravilima Creative Commons licencije koja omogućava da preuzmete djelo, podijelite ga s drugima uz 
uvjet da navođenja autora, ne upotrebljavate ga u komercijalne svrhe te dijelite pod istim uvjetima [Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License HR][cc-by-nc-sa].
>
> ### Napomena:
>
> Svi paketi distribuiraju se pod vlastitim licencama.
> Svi upotrijebleni materijali  (slike, modeli, animacije, ...) distribuiraju se pod vlastitim licencama.

[![CC BY-NC-SA 4.0][cc-by-nc-sa-image]][cc-by-nc-sa]

[cc-by-nc-sa]: https://creativecommons.org/licenses/by-nc/4.0/deed.hr 
[cc-by-nc-sa-image]: https://licensebuttons.net/l/by-nc-sa/4.0/88x31.png
[cc-by-nc-sa-shield]: https://img.shields.io/badge/License-CC%20BY--NC--SA%204.0-lightgrey.svg

Orginal [![cc0-1.0][cc0-1.0-shield]][cc0-1.0]
>
>COPYING: All the content within this repository is dedicated to the public domain under the CC0 1.0 Universal (CC0 1.0) Public Domain Dedication.
>
[![CC0-1.0][cc0-1.0-image]][cc0-1.0]

[cc0-1.0]: https://creativecommons.org/licenses/by/1.0/deed.en
[cc0-1.0-image]: https://licensebuttons.net/l/by/1.0/88x31.png
[cc0-1.0-shield]: https://img.shields.io/badge/License-CC0--1.0-lightgrey.svg

### Reference na licenciranje repozitorija


