# SPECIFICATION.md

## Projekt neve
Gázóra állás bejelentő

## Projekt rövid leírása
A projekt célja egy Android alapú mobilalkalmazás készítése, amely lehetővé teszi a felhasználók számára a gázóra állások rögzítését, megtekintését, módosítását és törlését. Az alkalmazás kezeli a fogyasztási helyeket, a hozzájuk tartozó mérőket, valamint emlékeztetőket is biztosít a rendszeres diktáláshoz.

Az alkalmazás két szerepkört támogat:
- **USER**: saját adatok kezelése, óraállások rögzítése, előzmények megtekintése, emlékeztetők kezelése
- **ADMIN**: felhasználók kezelése, szerepkörök módosítása, fogyasztási helyek és mérők kezelése

## Funkcionális követelmények

### Autentikáció
- regisztráció
- bejelentkezés
- kijelentkezés
- admin felhasználó automatikus létrehozása az alkalmazás indulásakor

### Jogosultságkezelés
- USER és ADMIN szerepkör támogatása
- admin funkciók csak admin felhasználó számára legyenek elérhetők
- az admin felület védett képernyő

### Óraállás kezelés
- új óraállás rögzítése
- mérő kiválasztása az óraállás rögzítésekor
- óraállások listázása
- óraállás részleteinek megtekintése
- óraállás módosítása
- óraállás törlése

### Emlékeztetők
- új emlékeztető létrehozása
- emlékeztetők listázása
- emlékeztető törlése

### Fogyasztási helyek kezelése
- új fogyasztási hely létrehozása
- fogyasztási helyek listázása
- fogyasztási hely törlése

### Mérők kezelése
- új mérő létrehozása
- fogyasztási hely kiválasztása mérő létrehozásakor
- mérők listázása
- mérő törlése

### Admin funkciók
- felhasználók listázása
- felhasználó törlése
- felhasználó szerepkörének módosítása
- saját admin felhasználó védelme törlés és szerepkörváltás ellen

### Lista műveletek
- keresés az óraállások listájában
- szűrés mérő szerint
- rendezés óraállás alapján növekvő és csökkenő sorrendben

### Navigáció
- többképernyős navigáció
- deep link támogatás az óraállások listájára:
  - `gazora://readings`

## Nem-funkcionális követelmények

### Technológiai döntések
- programozási nyelv: **Java**
- platform: **Android**
- adattárolás: **Room adatbázis**
- navigáció: **Intent alapú Activity navigáció**
- tesztelés: **JUnit alapú unit tesztek**, valamint Android integrációs teszt

### UI és használhatóság
- több képernyő támogatása
- egyszerű, átlátható felhasználói felület
- touch-barát gombok és input mezők
- dátumválasztás DatePicker segítségével
- listák és űrlapok külön képernyőkön
- admin és user funkciók elkülönítése

### Perzisztencia
- az adatok az alkalmazás bezárása után is megmaradnak
- a Room adatbázis biztosítja az adatok tárolását

### Karbantarthatóság
- réteges projektstruktúra
- külön `ui`, `data`, `dao`, `database` csomagok
- külön entitások és adatkezelő réteg

## Felhasználói szerepkörök

### USER
A felhasználó:
- regisztrálhat
- bejelentkezhet
- kijelentkezhet
- új óraállást rögzíthet
- listázhatja a saját rögzítéseket
- szerkesztheti és törölheti az óraállásokat
- emlékeztetőket kezelhet

### ADMIN
Az admin:
- minden USER funkcióval rendelkezik
- megtekintheti a felhasználók listáját
- módosíthatja más felhasználók szerepkörét
- törölhet felhasználókat
- kezelheti a fogyasztási helyeket
- kezelheti a mérőket

## Képernyőlista

1. **LoginActivity**
   - email és jelszó alapú bejelentkezés
   - navigáció a regisztrációhoz

2. **RegisterActivity**
   - új felhasználó létrehozása

3. **DashboardActivity**
   - főmenü
   - navigáció az óraállásokhoz, emlékeztetőkhöz és admin felülethez
   - kijelentkezés

4. **NewReadingActivity**
   - új óraállás rögzítése
   - mérő kiválasztása
   - dátum kiválasztása
   - megjegyzés megadása

5. **ReadingListActivity**
   - óraállások listázása
   - keresés
   - szűrés
   - rendezés

6. **ReadingDetailActivity**
   - kiválasztott óraállás részletei
   - szerkesztés
   - törlés

7. **ReminderActivity**
   - emlékeztetők kezelése
   - létrehozás és törlés

8. **AdminActivity**
   - admin funkciók gyűjtőképernyője
   - felhasználók kezelése
   - navigáció a fogyasztási helyek és mérők kezeléséhez

9. **PropertyActivity**
   - fogyasztási helyek kezelése

10. **MeterActivity**
   - mérők kezelése

## Navigációs terv

### Normál felhasználói navigáció
- LoginActivity → DashboardActivity
- LoginActivity → RegisterActivity
- DashboardActivity → NewReadingActivity
- DashboardActivity → ReadingListActivity
- DashboardActivity → ReminderActivity
- DashboardActivity → LoginActivity (kijelentkezés)

### Óraállás navigáció
- ReadingListActivity → ReadingDetailActivity
- ReadingDetailActivity → NewReadingActivity (szerkesztés)
- NewReadingActivity → ReadingListActivity

### Admin navigáció
- DashboardActivity → AdminActivity
- AdminActivity → PropertyActivity
- AdminActivity → MeterActivity

### Deep link
- `gazora://readings` → ReadingListActivity

## Összefoglalás
Az alkalmazás célja egy jól használható, több szerepkört kezelő, perzisztens adattárolással rendelkező Android mobilalkalmazás létrehozása, amely támogatja a gázóraállások rögzítését, a kapcsolódó mérők és fogyasztási helyek kezelését, valamint az adminisztrációs feladatokat.