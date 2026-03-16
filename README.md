# Gázóra állás bejelentő mobil alkalmazás

## Projekt leírás

A projekt célja egy Android alapú mobilalkalmazás készítése, amely
lehetővé teszi a felhasználók számára a gázóra állások rögzítését,
visszakeresését és kezelését. Az alkalmazás támogatja a fogyasztási
helyek és mérők kezelését, valamint emlékeztetők létrehozását a
rendszeres diktáláshoz.

Az alkalmazás két szerepkört kezel: - **Felhasználó (USER)** -- saját
óraállás rögzítése és megtekintése\
- **Admin (ADMIN)** -- felhasználók, fogyasztási helyek és mérők
kezelése

Az alkalmazás perzisztens adattárolást használ **Room adatbázissal**.

------------------------------------------------------------------------

# Fő funkciók

## Felhasználói funkciók

-   regisztráció
-   bejelentkezés
-   kijelentkezés
-   új gázóra állás rögzítése
-   korábbi óraállások listázása
-   óraállás módosítása
-   óraállás törlése
-   emlékeztetők létrehozása

A felhasználó a rögzítés során kiválaszthatja, melyik mérőhöz tartozik
az adott óraállás.

------------------------------------------------------------------------

## Admin funkciók

Az admin felhasználó külön admin felületet ér el, ahol: - felhasználók
listázása - felhasználók törlése - felhasználói szerepkör módosítása -
fogyasztási helyek kezelése - mérők kezelése

Az alkalmazás indulásakor automatikusan létrejön egy admin felhasználó:

email: admin@gazora.hu\
password: admin123

------------------------------------------------------------------------

# Adatmodell

## User

id\
name\
email\
password\
role

## Property

id\
name\
address

## Meter

id\
propertyId\
meterNumber\
type

## Reading

id\
meterId\
date\
value\
note

## Reminder

id\
title\
date

Kapcsolat: Property → Meter → Reading

------------------------------------------------------------------------

# Alkalmazás architektúra

ui - login - dashboard - reading - reminder - admin

data - entity - dao - database

A perzisztens adattárolás **Room ORM segítségével** történik.

------------------------------------------------------------------------

# Lista funkciók

Az óraállások listájában a következő funkciók érhetők el:

-   keresés
-   mérő szerinti szűrés
-   rendezés növekvő / csökkenő sorrendben

------------------------------------------------------------------------

# Használt technológiák

-   Java
-   Android SDK
-   Room Database
-   Android UI komponensek
-   Intent alapú navigáció

------------------------------------------------------------------------

# Tesztelés

Az alkalmazás funkcionális tesztelése manuálisan történt az alábbi
funkciókra:

-   regisztráció
-   bejelentkezés
-   óraállás rögzítés
-   lista funkciók
-   admin műveletek
-   adatbázis műveletek

------------------------------------------------------------------------

# Telepítés

Az alkalmazás Android Studio segítségével fordítható.

Run → app

------------------------------------------------------------------------

## AI támogatás

A projekt fejlesztése során AI alapú eszközök (ChatGPT) kerültek használatra.

Az AI az alábbi területeken nyújtott támogatást:
- architektúra tervezés
- adatmodell kialakítása
- Room adatbázis használata
- navigáció és UI struktúra
- tesztelési stratégia
- dokumentáció készítése

Az AI által adott javaslatok minden esetben fejlesztői ellenőrzés után kerültek be a projektbe.


# Fejlesztő

\[Juhász Ádám\]\
\[WDDNVO\]
