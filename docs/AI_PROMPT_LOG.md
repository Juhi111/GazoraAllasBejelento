# AI_PROMPT_LOG.md

## AI használat a projekt során

A projekt fejlesztése során AI alapú eszközök (ChatGPT) kerültek használatra a következő feladatok támogatására:

- projektstruktúra megtervezése
- Android architektúra kialakítása
- Room adatbázis integráció
- DAO és entitások tervezése
- UI navigációs struktúra
- unit tesztek készítése
- dokumentáció készítése

Az AI eszköz nem automatikus kódgenerátorként, hanem fejlesztési segédeszközként lett használva.

---

# Jelentős promptok

## 1. Projektötlet és architektúra

Prompt:

Kérlek segíts egy Android mobilalkalmazás projekt struktúrájának megtervezésében, amely gázóra állások kezelésére szolgál.

AI válasz összefoglalása:

Az AI javaslatot adott egy több képernyős Android alkalmazásra, amely Room adatbázist használ és külön kezeli az entitásokat, DAO-kat és az UI réteget.

---

## 2. Entitások megtervezése

Prompt:

Milyen entitásokat érdemes használni egy gázóra állás kezelő mobilalkalmazásban?

AI válasz:

Az AI az alábbi entitásokat javasolta:

- User
- Property
- Meter
- Reading
- Reminder

Ezek később a projekt végleges adatmodelljének alapját képezték.

---

## 3. Room adatbázis integráció

Prompt:

Hogyan lehet Room adatbázist használni Android alkalmazásban több entitással?

AI válasz:

Az AI javasolta:

- Room Entity osztályok
- DAO interfészek
- AppDatabase singleton használata

Ez alapján készült el az alkalmazás adatkezelő rétege.

---

## 4. Navigációs struktúra

Prompt:

Hogyan érdemes felépíteni egy több képernyős Android alkalmazás navigációját?

AI válasz:

Az AI egy Activity alapú navigációs struktúrát javasolt, amelyben külön képernyők kezelik a login, dashboard, reading és admin funkciókat.

---

## 5. Admin szerepkör megvalósítása

Prompt:

Hogyan lehet Android alkalmazásban role alapú hozzáférést kezelni?

AI válasz:

Az AI javasolta:

- role mező a User entitásban
- admin UI elemek elrejtése USER szerepkörnél
- admin felhasználó automatikus létrehozása

Ez a megoldás került implementálásra.

---

## 6. Deep link támogatás

Prompt:

Hogyan lehet deep linket implementálni Android alkalmazásban?

AI válasz:

Az AI javasolta az AndroidManifest intent-filter használatát, amely lehetővé teszi a `gazora://readings` link megnyitását.

---

## 7. Unit tesztek készítése

Prompt:

Milyen unit teszteket érdemes írni egy Android Room adatbázis projektben?

AI válasz:

Az AI javasolta:

- entity tesztek
- DAO tesztek
- lista logika tesztelése
- integrációs teszt Room adatbázissal

A projektben végül 10 unit teszt és 1 integrációs teszt készült.

---

## 8. Keresés és rendezés implementálása

Prompt:

Hogyan lehet listákban keresést és rendezést implementálni Android alkalmazásban?

AI válasz:

Az AI javasolta:

- listák szűrését Java List feldolgozással
- Comparator alapú rendezést

Ez a megoldás került a ReadingListActivity-be.

---

## 9. Dokumentáció generálása

Prompt:

Segíts Android mobilprojekt dokumentációs fájlok elkészítésében.

AI válasz:

Az AI javaslatokat adott a következő dokumentációk elkészítésére:

- SPECIFICATION.md
- DATAMODEL.md
- COMPONENTS.md
- README.md

---

## 10. Tesztstratégia

Prompt:

Hogyan lehet teljesíteni egy mobil projekt tesztelési követelményeit?

AI válasz:

Az AI javasolta:

- legalább 10 unit teszt
- 1 integrációs teszt
- DAO és entity tesztelés

Ez alapján készült el a projekt tesztelési része.

---

# Döntéshozatal

## 1. Room adatbázis választása

Az AI több lehetőséget javasolt (Firebase, REST API, Room).

A projekt végül Room adatbázist használ, mert:

- egyszerűbb lokális fejlesztés
- nincs szükség külső backend infrastruktúrára

---

## 2. Activity alapú architektúra

Az AI MVVM architektúrát is javasolt.

A projekt végül egy egyszerűbb Activity + DAO alapú architektúrát használ, mert:

- kisebb projekt
- gyorsabb implementáció
- könnyebb tanulási célra

---

## 3. Admin felhasználó kezelése

Az AI több lehetőséget adott:

- admin kód regisztrációkor
- admin backend
- automatikus admin user

A projekt az automatikus admin felhasználót választotta.

---

## 4. Lista műveletek

Az AI javasolta:

- keresés
- szűrés
- rendezés

Mindhárom implementálásra került a ReadingListActivity-ben.

---

## 5. Tesztelési stratégia

Az AI javasolta:

- entity tesztek
- lista logika tesztek
- integrációs teszt Room adatbázissal

Ez a struktúra lett megvalósítva.

---

# AI hibák és korrekciók

## 1. DAO teszt generálás

Az AI egy DAO tesztet javasolt, amely nem működött Android környezet nélkül.

A megoldás az volt, hogy a DAO tesztet Android instrumented tesztként futtatjuk.

---

## 2. Deep link konfiguráció

Az AI első javaslata nem tartalmazta a szükséges BROWSABLE kategóriát.

Ez manuálisan került hozzáadásra az AndroidManifest fájlhoz.

---

# Összegzés

Az AI eszköz jelentős segítséget nyújtott a projekt tervezésében és fejlesztésében, de a végső döntéseket és implementációkat minden esetben fejlesztői ellenőrzés és módosítás követte.

Az AI használata így a fejlesztési folyamat hatékony támogatását szolgálta, nem pedig teljes automatizálását.