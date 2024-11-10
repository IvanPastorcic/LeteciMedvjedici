# Programsko inženjerstvo


# Opis projekta
Ovaj projekt je reultat timskog rada u sklopu projeknog zadatka kolegija [Programsko inženjerstvo](https://www.fer.unizg.hr/predmet/proinz) na Fakultetu elektrotehnike i računarstva Sveučilišta u Zagrebu. 

Cilj ovog studentskog timskog projekta je osmisliti i razviti aplikaciju koja omogućava brzu reakciju i učinkovitiju koordinaciju između građana, vlasti i humanitarnih organizacija. Rješavamo problem spore i loše komunikacije između vlasti i javnosti u kriznim situacijama. Želimo olakšati dobivanje pomoći i relevantnih informacija unesrećnima. 

Ovim projektom želimo usavršiti svoja znanja i vještine izrade programske potpore, sposobnosti rada u timu i kolegijalnost. 


# Funkcijski zahtjevi
## Funkcionalni zahtjevi

| ID zahtjeva | Opis | Prioritet | Izvor | Kriteriji prihvaćanja |
|---|---|---|---|---|
| F-01 | Aplikacija mora omogućiti lociranje prijava na interaktivnoj mapi. | Srednji | Zahtjev dionika | Korisnik može vidjeti lokacije prijašnjih prijava na interaktivnoj mapi |
| F-02 | Sustav omogućuje korisnicima kreiranje računa pomoću e-mail adrese. | Visok | Zahtjev dionika | Korisnik se može registrirati e-mailom, primiti e-mail za potvrdu i uspješno se prijaviti. | 
| F-02.1 | Sustav obavještava korisnika o prikupljanju i načinu korištenja osobnih podataka pri registraciji | Nizak | Postojeći sustav | Korisnik prije stvaranja računa dobija iskočnu obavijest s privolom za prikupljanje podataka. |
| F-02.1.1 | Korisnik može odbiti prikupljanje podataka | Nizak | Postojeći sustav | Korisnik ima opciju odbiti prikupljanje osobnih podataka na iskočnoj obavijesti. |
| F-03 | Sustav omogućuje korisnicima prijavu u sustav pomoću već postojećeg korisničkog računa. | Visok | Zahtjev dionika | Korisnik se može prijaviti u sustav e-mailom i lozinkom. |
| F-04 | Aplikacija građanima omogućava prijavu informacija o prirodnim nepogodama. | Visok | Zahtjev dionika | Korisnik može prijaviti vrstu, lokaciju, sliku i koordinate nepogode te dodati kratki opis. |
| F-05 | Aplikacija građanima omogućuje praćenje statusa njihovih prijava. | Srednji | Zahtjev dionika | Korisnik ima uvid u stanje svojih prijava. |
| F-05.1 | Aplikacija obavještava korisnika o promjenama statusa njegovih prijava putem e-maila. | Srednji | Zahtjev dionika | Korisnik prima obavijesti o promjenama statusa prijava putem e-maila. |
| F-05.2 | Aplikacija obavještava korisnika o promjenama statusa njegovih prijava putem push notifikacija. | Srednji | Zahtjev dionika | Korisnik prima obavijesti o promjenama statusa prijava putem push notifikacija. |
| F-06 | Aplikacija obavještava korisnika o novim prijavljenim nepogodama. | Visok | Zahtjev dionika | Korisnik prima obavijesti o novim prijavljenim nepogodama. | 
| F-06.1 | Aplikacija obavještava korisnika o novim prijavljenim nepogodama putem push notifikacija. | Visok | Zahtjev dionika | Korisnik prima obavijesti o novim prijavljenim nepogodama putem push notifikacija. | 
| F-06.2 | Aplikacija obavještava korisnika o novim prijavljenim nepogodama putem e-maila. | Visok | Zahtjev dionika | Korisnik prima obavijesti o novim prijavljenim nepogodama putem e-maila. |
| F-06.3 | Aplikacija obavještava korisnika o novim prijavljenim nepogodama ovisno o korisnikovom izboru. | Visok | Zahtjev dionika | Korisnik prima obavijesti o novim prijavljenim nepogodama ovisno o izboru regija. |
| F-06.3.1 | Korisnik može izabrati regije za koje želi primati obavijesti. | Srednji | Zahtjev dionika | Korisnik ima opciju izbora regija za primanje obavijesti o novim prirodnim nepogodama. |
| F-06.4 | Aplikacija obavještava korisnika o novim prijavljenim nepogodama ovisno o korisnikovoj lokaciji. | Visok | Zahtjev dionika | Korisnik prima obavijesti o novim prijavljenim nepogodama ovisno o svojoj lokaciji. |
| F-07 | Aplikacija omogućuje korisnicima pristup važnim informacijama za krizne situacije. | Srednji | Zahtjev dionika | Korisnik unutar aplikacije može pristupiti važnim informacijama u slučaju prirodnih nepogoda. |
| F-07.1 | Aplikacija omogućuje korisnicima pristup važnim informacijama o sigurnosnim mjerama u kriznim situacijama. | Srednji | Zahtjev dionika | Korisnik unutar aplikacije može pristupiti važnim informacijama o sigurnosnim mjerama u slučaju prirodnih nepogoda. |
| F-07.2 | Aplikacija prikazuje korisnicima informacije o najbližim skloništima u kriznim situacijama. | Srednji | Zahtjev dionika | Korisnik unutar aplikacije može pristupiti informacijama o najbližim skloništima u slučaju prirodnih nepogoda. |
| F-08 | Aplikacija omogućava slanje anonimne prijave. | Visok | Zahtjev dionika | Korisnik može prijaviti nepogodu bez prijave u sustav. |
| F-08.1 | Status anonimne prijave se može pratiti putem jedinstvenog identifikacijskog broja. | Srednji | Zahtjev dionika | Korisnik može pratiti status anonimne prijave putem jedinstvenog identifikacijskog broja. |
| F-09 | Aplikacija građaninu omogućuje prijavu nužnih potreba uz prijavu nepogode. | Visok | Zahtjev dionika | Korisnik može prijaviti svoje nužne potrebe neposredno nakon prijave prirodne nepogode. |
| F-09.1 | Aplikacija građaninu omogućuje prijavu potreba za hranom uz prijavu nepogode. | Visok | Zahtjev dionika | Korisnik može prijaviti svoje potrebe za hranom neposredno nakon prijave prirodne nepogode. |
| F-09.2 | Aplikacija građaninu omogućuje prijavu potreba za skloništem uz prijavu nepogode. | Visok | Zahtjev dionika | Korisnik može prijaviti svoje potrebe za skloništem neposredno nakon prijave prirodne nepogode. |
| F-09.3 | Aplikacija građaninu omogućuje prijavu potreba za medicinskom intervencijom uz prijavu nepogode. | Visok | Zahtjev dionika | Korisnik može prijaviti svoje potrebe za medicinskom intervencijom neposredno nakon prijave prirodne nepogode. |
| F-10 | Aplikacija vlastima omogućava pristup informacijama o svim prijavama u sustav. | Nizak | Zahtjev dionika | Vlasti imaju pristup podacima o prijavama u sustav. |
| F-11 | Aplikacija omogućava vlastima pristup svim podacima vezanima uz prirodne nepogode. | Nizak | Zahtjev dionika | Vlasti imaju pristup podacima o prijavama prirodnih nepogoda. |
| F-12 | Aplikacija humanitarnim organizacijama omogućuje pregled potreba građana. | Visok | Zahtjev dionika | Vlasti imaju pristup podacima o potrebama građana. |
| F-12.1 | Aplikacija humanitarnim organizacijama omogućuje filtriranje potreba građana. | Nizak | Kreativnost | Humanitarne organizacije imaju opciju filtriranja ovisno o vrsti potrebe građana. |  
| F-13 | Aplikacija humanitarnim organizacijama omogućuje dodavanje informacija o dostupnim resursima. | Srednji | Zahtjev dionika | Humanitarne organizacije imaju opciju dodati informacije o dostupnim resursima za krizne situacije unutar aplikacije. |
| F-13.1 | Dostupni resursi uključuju: voda, hrana, kapacitet skloništa, kutija za prvu pomoć, vreća pjeska, grijalica. | Srednji | Zahtjev dionika | Humanitarne organizacije imaju unutar aplikacije opciju dodati informacije o dostupnim količinama vode, hrane, kutija za prvu pomoć, vreći pijeska i grijalica te trenutnom kapacitetu skloništa za krizne situacije. |
| F-13.1.1 | Humanitarne organizacije imaju pregled količine resursa. | Nizak | Kreativnost | Humanitarne organizaciju mogu vidjeti količinu dostupnim resursa unutar aplikacije. | 
| F-14 | Aplikacija humanitarnim organizacijama omogućuje dodavanje informacija o akcijama pomoći. | Srednji | Zahtjev dionika | Humanitarne organizacije imaju opciju dodati informacije o budućim akcijama pomoći unesrećenima. |
| F-15 | Aplikacija administratorima omogućava pregled svih prijava. | Visok | Zahtjev dionika | Administratori mogu u aplikaciji vidjeti sve prijave nepogoda. |
| F-16 | Aplikacija administratorima omogućava promjenu statusa prijave. | Visok | Zahtjev dionika | Administratori mogu promjeniti status prijava u aplikaciji. |
| F-16.1 | Aplikacija administratorima omogućava odobravanje prijava. | Visok | Zahtjev dionika | Administratori mogu odobriti prijave u aplikaciji. |
| F-16.2 | Aplikacija administratorima omogućava odbijanje prijava. | Visok | Zahtjev dionika | Administratori mogu odbiti prijave u aplikaciji. |
| F-17 | Aplikacija mora omogućiti korisniku brisanje spremljenih osobnih podataka. | Srednji | Postojeći sustav | Korisnik može unutar postavki za svoj profil tražiti brisanje svojih osobnih podataka. |
| F-17.1 | Aplikacija mora korisniku dati obavijest o pokušaju brisanja podataka putem e-maila. | Nizak | Postojeći sustav | Korisnik dobija e-mail potvrdu za pokušaj brisanja osobnih podataka. |
| F-18 | Aplikacija mora korisnika obavijestiti o načini prikupljanja i korištenja osobnih podataka. | Nizak | Postojeći sustav | Korisnik pri prvom korištenju aplikacije dobija iskočnu obavijest s privolom za prikupljanje i korištenje osobnih podataka. |
| F-18.1 | Korisnik mora moći odbiti prikupljanje i korištenje osobnih podataka. | Nizak | Postojeći sustav | Korisnik može odbiti prikupljanje i korištenje osobnih podataka. |

## Ostali zahtjevi

| ID zahtjeva | Opis | Prioritet |
| --- | --- | --- |
| NF-1 | Aplikacija slijedi Opću uredbu o zaštiti osobnih podataka | Visok |
| NF-2 | Aplikacija mora biti responzivna na različitim uređajima | Nizak |
| NF-3 | Aplikacija mora biti usklađena sa standardima pristupačnosti | Nizak |
| NF-4.1 | Sustav treba biti oblikovan tako da omogućuje jednostavno održavanje. | Visok |
| NF-4.1.1 | Sustav treba imati dovoljnu dokumentaciju. | Visok |
| NF-4.1.1.1 | Kôd sustava treba biti dokumentiran prema "Code Conventions for the Java Programming Language" dostupnim na [Oracle](https://www.oracle.com/java/technologies/cc-java-programming-language.html). | Visok |
| NF-4.1.1.2 | Sustav treba biti opisan putem dokumenta oblikovanja /SRS/. | Visok |
| NF-4.1.1.3 | Sustav treba biti popraćen "Priručnikom za rad" koji opisuje pravilnu upotrebu sustava. | Visok |


# Tehnologije

> Frontend - React.js <br>
> Backend - Spring Boot i Maven <br>
> Baza podataka - postgreSQL <br>
> Komunikacija - Discord <br>
> Dizajn - Figma <br>
> UML - Astah UML 10.0.0 <br>
> Testiranje - Postman <br>
> Deployment - Render <br>

# Članovi tima 
> Elma Vuran - voditelj, full-stack <br>
> Lana Zekušić - frontend, dizajn <br>
> Ivan Pastorčić - backend, baza, dokumentacija, DevOps <br>
> Natali Žegarac - backend, baza <br>
> Silvija Rade - frontend <br>
> Krešimir Horvat - frontend <br>
> Iva Sulić - frontend <br>

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


