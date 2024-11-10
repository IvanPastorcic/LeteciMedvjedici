# Programsko inženjerstvo


# Opis projekta
Ovaj projekt je rezultat timskog rada u sklopu projektnog zadatka kolegija [Programsko inženjerstvo](https://www.fer.unizg.hr/predmet/proinz) na Fakultetu elektrotehnike i računarstva Sveučilišta u Zagrebu. 

Cilj ovog studentskog timskog projekta je osmisliti i razviti aplikaciju koja omogućava brzu reakciju i učinkovitiju koordinaciju između građana, vlasti i humanitarnih organizacija. Rješavamo problem spore i loše komunikacije između vlasti i javnosti u kriznim situacijama. Želimo olakšati dobivanje pomoći i relevantnih informacija unesrećenima. 

Ovim projektom želimo usavršiti svoja znanja i vještine izrade programske potpore, sposobnosti rada u timu i kolegijalnost. 


# Funkcijski zahtjevi
## Funkcionalni zahtjevi

| ID zahtjeva | Opis                                                                                                          | Prioritet | Izvor            | Kriteriji prihvaćanja                                                                                   |
| ----------- | ------------------------------------------------------------------------------------------------------------- | -------   | ---------------- | ------------------------------------------------------------------------------------------------------- |
| F-01        | Aplikacija mora omogućiti lociranje prijava na interaktivnoj mapi.                                            | Srednji   | Zahtjev dionika  | Korisnik može vidjeti lokacije prijašnjih prijava na interaktivnoj mapi                                 |
| F-02        | Sustav omogućuje korisnicima kreiranje računa pomoću e-mail adrese.                                           | Visok     | Zahtjev dionika  | Korisnik se može registrirati e-mailom, primiti e-mail za potvrdu i uspješno se prijaviti.              |
| F-02.1      | Sustav obavještava korisnika o prikupljanju i načinu korištenja osobnih podataka pri registraciji.            | Nizak     | Postojeći sustav | Korisnik prije stvaranja računa dobiva push obavijest s privolom za prikupljanje podataka.              |
| F-02.1.1    | Korisnik može odbiti prikupljanje podataka.                                                                   | Nizak     | Postojeći sustav | Korisnik ima opciju odbiti prikupljanje osobnih podataka na push obavijesti.                            |
| F-03        | Sustav omogućuje korisnicima prijavu u sustav s pomoću postojećeg korisničkog računa.                         | Visok     | Zahtjev dionika  | Korisnik se može prijaviti u sustav e-mailom i lozinkom.                                                |
| F-04        | Aplikacija građanima omogućava prijavu informacija o prirodnim nepogodama.                                    | Visok     | Zahtjev dionika  | Korisnik može prijaviti vrstu, lokaciju, sliku i koordinate nepogode te dodati kratki opis.             |
| F-05        | Aplikacija građanima omogućuje praćenje statusa njihovih prijava.                                             | Srednji   | Zahtjev dionika  | Korisnik ima uvid u stanje svojih prijava.                                                              |
| F-05.1      | Aplikacija obavještava korisnika o promjenama statusa njegovih prijava putem e-maila.                         | Srednji   | Zahtjev dionika  | Korisnik prima obavijesti o promjenama statusa prijava putem e-maila.                                   |
| F-05.2      | Aplikacija obavještava korisnika o promjenama statusa njegovih prijava putem push notifikacija.               | Srednji   | Zahtjev dionika  | Korisnik prima obavijesti o promjenama statusa prijava putem push notifikacija.                         |
| F-06        | Aplikacija obavještava korisnika o novim prijavljenim nepogodama.                                             | Visok     | Zahtjev dionika  | Korisnik prima obavijesti o novim prijavljenim nepogodama.                                              |
| F-06.1      | Aplikacija obavještava korisnika o novim prijavljenim nepogodama putem push notifikacija.                     | Visok     | Zahtjev dionika  | Korisnik prima obavijesti o novim prijavljenim nepogodama putem push notifikacija.                      |
| F-06.2      | Aplikacija obavještava korisnika o novim prijavljenim nepogodama putem e-maila.                               | Visok     | Zahtjev dionika  | Korisnik prima obavijesti o novim prijavljenim nepogodama putem e-maila.                                |
| F-06.3      | Aplikacija obavještava korisnika o novim prijavljenim nepogodama ovisno o korisnikovom izboru.                | Visok     | Zahtjev dionika  | Korisnik prima obavijesti o novim prijavljenim nepogodama ovisno o izboru regija.                       |
| F-06.3.1    | Korisnik može izabrati regije za koje želi primati obavijesti.                                                | Srednji   | Zahtjev dionika  | Korisnik ima opciju izbora regija za primanje obavijesti o novim prirodnim nepogodama.                  |
| F-06.4      | Aplikacija obavještava korisnika o novim prijavljenim nepogodama ovisno o korisnikovoj lokaciji.              | Visok     | Zahtjev dionika  | Korisnik prima obavijesti o novim prijavljenim nepogodama ovisno o svojoj lokaciji.                     |
| F-07        | Aplikacija omogućuje korisnicima pristup važnim informacijama za krizne situacije.                            | Srednji   | Zahtjev dionika  | Korisnik unutar aplikacije može pristupiti važnim informacijama u slučaju prirodnih nepogoda            |
| F-07.1      | Aplikacija omogućuje korisnicima pristup važnim informacijama o sigurnosnim mjerama u kriznim situacijama.    | Srednji   | Zahtjev dionika  | Korisnik unutar aplikacije može pristupiti važnim informacijama o sigurnosnim mjerama u slučaju prirodnih nepogoda.                                                                                                                                                                                                                                                    |
| F-07.2      | Aplikacija prikazuje korisnicima informacije o najbližim skloništima u kriznim situacijama.                   | Srednji   | Zahtjev dionika  | Korisnik unutar aplikacije može pristupiti informacijama o najbližim skloništima u slučaju prirodnih nepogoda.                                                                                                                                                                                                                                                              |
| F-08        | Aplikacija omogućava slanje anonimne prijave.                                                                 | Visok     | Zahtjev dionika  | Korisnik može prijaviti nepogodu bez prijave u sustav.                                                  |
| F-08.1      | Status anonimne prijave se može pratiti putem jedinstvenog identifikacijskog broja.                           | Srednji   | Zahtjev dionika  | Korisnik može pratiti status anonimne prijave putem jedinstvenog identifikacijskog broja.               |
| F-09        | Aplikacija građaninu omogućuje prijavu potreba uz prijavu nepogode.                                           | Visok     | Zahtjev dionika  | Korisnik može prijaviti svoje potrebe neposredno nakon prijave prirodne nepogode.                       |
| F-09.1      | Aplikacija građaninu omogućuje prijavu potreba za hranom uz prijavu nepogode.                                 | Visok     | Zahtjev dionika  | Korisnik može prijaviti svoje potrebe za hranom neposredno nakon prijave prirodne nepogode.             |
| F-09.2      | Aplikacija građaninu omogućuje prijavu potreba za skloništem uz prijavu nepogode.                             | Visok     | Zahtjev dionika  | Korisnik može prijaviti svoje potrebe za skloništem neposredno nakon prijave prirodne nepogode.         |
| F-09.3      | Aplikacija građaninu omogućuje prijavu potreba za medicinskom intervencijom uz prijavu nepogode.              | Visok     | Zahtjev dionika  | Korisnik može prijaviti svoje potrebe za medicinskom intervencijom neposredno nakon prijave prirodne nepogode.                                                                                                                                                                                                                                                              |
| F-10        | Aplikacija vlastima omogućava pristup informacijama o svim prijavama u sustav.                                | Nizak     | Zahtjev dionika  | Vlasti imaju pristup podacima o prijavama u sustav.                                                     |
| F-11        | Aplikacija omogućava vlastima pristup svim podacima vezanima uz prirodne nepogode.                            | Nizak     | Zahtjev dionika  | Vlasti imaju pristup podacima o prijavama prirodnih nepogoda.                                           |
| F-12        | Aplikacija humanitarnim organizacijama omogućuje pregled potreba građana.                                     | Visok     | Zahtjev dionika  | Vlasti imaju pristup podacima o potrebama građana.                                                      |
| F-12.1      | Aplikacija humanitarnim organizacijama omogućuje filtriranje potreba građana.                                 | Nizak     | Kreativnost      | Humanitarne organizacije imaju opciju filtriranja ovisno o vrsti potrebe građana.                       |
| F-13        | Aplikacija humanitarnim organizacijama omogućuje dodavanje informacija o dostupnim resursima.                 | Srednji   | Zahtjev dionika  | Humanitarne organizacije imaju opciju dodati informacije o dostupnim resursima za krizne situacije unutar aplikacije.                                                                                                                                                                                                                                                     |
| F-13.1      | Dostupni resursi uključuju: voda, hrana, kapacitet skloništa, kutija za prvu pomoć, vreća pijeska, grijalica. | Srednji   | Zahtjev dionika  | Humanitarne organizacije imaju unutar aplikacije opciju dodati informacije o dostupnim količinama vode, hrane, kutija za prvu pomoć, vreći pijeska i grijalica te trenutnom kapacitetu skloništa za krizne situacije.                                                                                                                                                          |
| F-13.1.1    | Humanitarne organizacije imaju pregled količine resursa.                                                      | Nizak     | Kreativnost      | Humanitarne organizaciju mogu vidjeti količinu dostupnim resursa unutar aplikacije.                     |
| F-14        | Aplikacija humanitarnim organizacijama omogućuje dodavanje informacija o akcijama pomoći.                     | Srednji   | Zahtjev dionika  | Humanitarne organizacije imaju opciju dodati informacije o budućim akcijama pomoći unesrećenima.        |
| F-15        | Aplikacija administratorima omogućava pregled svih prijava.                                                   | Visok     | Zahtjev dionika  | Administratori mogu u aplikaciji vidjeti sve prijave nepogoda.                                          |
| F-16        | Aplikacija administratorima omogućava promjenu statusa prijave.                                               | Visok     | Zahtjev dionika  | Administratori mogu promijeniti status prijava u aplikaciji.                                            |
| F-16.1      | Aplikacija administratorima omogućava odobravanje prijava.                                                    | Visok     | Zahtjev dionika  | Administratori mogu odobriti prijave u aplikaciji.                                                      |
| F-16.2      | Aplikacija administratorima omogućava odbijanje prijava.                                                      | Visok     | Zahtjev dionika  | Administratori mogu odbiti prijave u aplikaciji.                                                        |
| F-17        | Aplikacija mora omogućiti korisniku brisanje spremljenih osobnih podataka.                                    | Srednji   | Postojeći sustav | Korisnik može unutar postavki za svoj profil tražiti brisanje svojih osobnih podataka.                  |
| F-17.1      | Aplikacija mora korisniku dati obavijest o pokušaju brisanja podataka putem e-maila.                          | Nizak     | Postojeći sustav | Korisnik dobiva e-mail potvrdu za pokušaj brisanja osobnih podataka.                                    |
| F-18        | Aplikacija mora korisnika obavijestiti o načini prikupljanja i korištenja osobnih podataka.                   | Nizak     | Postojeći sustav | Korisnik pri prvom korištenju aplikacije dobiva push obavijest s privolom za prikupljanje i korištenje osobnih podataka.                                                                                                                                                                                                                                                      |
| F-18.1      | Korisnik mora moći odbiti prikupljanje i korištenje osobnih podataka.                                         | Nizak     | Postojeći sustav | Korisnik može odbiti prikupljanje i korištenje osobnih podataka.                                        |

## Ostali zahtjevi

| ID zahtjeva | Opis                                                                                                                                                                                              | Prioritet |
| ----------  | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------- |
| NF-1        | Aplikacija slijedi Opću uredbu o zaštiti osobnih podataka                                                                                                                                         | Visok     |
| NF-2        | Aplikacija mora biti responzivna na različitim uređajima                                                                                                                                          | Nizak     |
| NF-3        | Aplikacija mora biti usklađena sa standardima pristupačnosti                                                                                                                                      | Nizak     |
| NF-4.1      | Sustav treba biti oblikovan tako da omogućuje jednostavno održavanje.                                                                                                                             | Visok     |
| NF-4.1.1    | Sustav treba imati dovoljnu dokumentaciju.                                                                                                                                                        | Visok     |
| NF-4.1.1.1  | Kôd sustava treba biti dokumentiran prema "Code Conventions for the Java Programming Language" dostupnim na [Oracle](https://www.oracle.com/java/technologies/cc-java-programming-language.html). | Visok     |
| NF-4.1.1.2  | Sustav treba biti opisan putem dokumenta oblikovanja /SRS/.                                                                                                                                       | Visok     |
| NF-4.1.1.3  | Sustav treba biti popraćen "Priručnikom za rad" koji opisuje pravilnu upotrebu sustava.                                                                                                           | Visok     |
| NF-5        | Prijave nepogoda su omogućene samo unutar Republike Hrvatske                                                                                                                                      | Visok     |
| NF-5.1      | Korisnik može prijaviti nepogodu ne više od 30 km od trenutne lokacije                                                                                                                            | Visok     |
| NF-6        | Korisnik ne može prijaviti prirodnu nepogodu sat vremena nakon prethodne prijave, ako takva postoji                                                                                               | Visok     |

# Tehnologije

> Frontend - React.js<sup>[[1]](https://react.dev/)</sup> <br>
> Backend - Spring Boot<sup>[[2]](https://spring.io/)</sup> i Maven<sup>[[3]](https://maven.apache.org/)</sup> <br>
> Baza podataka - H2<sup>[[4]](https://www.h2database.com/html/main.html)</sup> <br>
> Dizajn - Figma<sup>[[5]](https://www.figma.com/)</sup> <br>
> UML - Astah UML 10.0.0<sup>[[6]](https://astah.net/)</sup> <br>
> Testiranje - Postman<sup>[[7]](https://www.postman.com/)</sup> <br>
> Deployment - Render<sup>[[8]](https://render.com/)</sup> <br>

# Članovi tima 
> Elma Vuran - voditelj, full-stack <br>
> Lana Zekušić - frontend, dizajn <br>
> Ivan Pastorčić - backend, baza, dokumentacija, DevOps <br>
> Natali Žegarac - backend, baza <br>
> Silvija Rade - frontend <br>
> Krešimir Horvat - frontend <br>
> Iva Sulić - frontend <br>

# Kontribucije
Tijekom rada na projektu se očekuje praćenje kodeksa ponašanja te etičkog kodeksa projekta. Više detalja pogledajte u [CONTRIBUTING.md](https://github.com/IvanPastorcic/LeteciMedvjedici/blob/main/CONTRIBUTING.md).



# 📝 Kodeks ponašanja [![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](CODE_OF_CONDUCT.md)
Sudionici na projektu prate [KODEKS PONAŠANJA STUDENATA FAKULTETA ELEKTROTEHNIKE I RAČUNARSTVA SVEUČILIŠTA U ZAGREBU](https://www.fer.hr/_download/repository/Kodeks_ponasanja_studenata_FER-a_procisceni_tekst_2016%5B1%5D.pdf) te poštuju
[etički kodeks IEEE-a](https://www.ieee.org/about/corporate/governance/p7-8.html).

# 📝 Licenca
Ovaj projekt je licenciran pod MIT licencom. Pogledajte [LICENSE](LICENSE) datoteku za više informacija.

<!-- Važeća (1) 
[![CC BY-NC-SA 4.0][cc-by-nc-sa-shield]][cc-by-nc-sa]

Ovaj repozitorij sadrži otvoreni obrazovni sadržaji (eng. Open Educational Resources)  i licenciran je prema pravilima Creative Commons licencije koja omogućava da preuzmete djelo, podijelite ga s drugima uz 
uvjet da navođenja autora, ne upotrebljavate ga u komercijalne svrhe te dijelite pod istim uvjetima [Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License HR][cc-by-nc-sa].
>
> ### Napomena:
>
> Svi paketi distribuiraju se pod vlastitim licencama.
> Svi upotrijebljeni materijali  (slike, modeli, animacije, ...) distribuiraju se pod vlastitim licencama.

[![CC BY-NC-SA 4.0][cc-by-nc-sa-image]][cc-by-nc-sa]

[cc-by-nc-sa]: https://creativecommons.org/licenses/by-nc/4.0/deed.hr 
[cc-by-nc-sa-image]: https://licensebuttons.net/l/by-nc-sa/4.0/88x31.png
[cc-by-nc-sa-shield]: https://img.shields.io/badge/License-CC%20BY--NC--SA%204.0-lightgrey.svg

Original [![cc0-1.0][cc0-1.0-shield]][cc0-1.0]
>
>COPYING: All the content within this repository is dedicated to the public domain under the CC0 1.0 Universal (CC0 1.0) Public Domain Dedication.
>
[![CC0-1.0][cc0-1.0-image]][cc0-1.0]

[cc0-1.0]: https://creativecommons.org/licenses/by/1.0/deed.en
[cc0-1.0-image]: https://licensebuttons.net/l/by/1.0/88x31.png
[cc0-1.0-shield]: https://img.shields.io/badge/License-CC0--1.0-lightgrey.svg -->
