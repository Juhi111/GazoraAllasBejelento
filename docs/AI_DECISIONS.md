# AI_DECISIONS.md

## Fejlesztési döntések AI támogatással

A projekt fejlesztése során az AI több lehetséges megoldást is javasolt. A végleges implementáció előtt minden esetben fejlesztői döntés született arról, hogy az adott javaslat elfogadásra, módosításra vagy elutasításra kerüljön.

---

## 1. Lokális adatbázis használata

### AI javaslat
Az AI javasolta Firebase, REST API vagy Room adatbázis használatát.

### Végső döntés
A projekt végül **Room adatbázist** használ.

### Indoklás
- egyszerűbb lokális fejlesztés
- nincs szükség külső backend infrastruktúrára
- offline működés is biztosítható
- a projekt méretéhez és céljához jól illeszkedik

### Döntés típusa
**Elfogadott, módosítás nélkül**

---

## 2. Architektúra kiválasztása

### AI javaslat
Az AI MVVM architektúrát javasolt ViewModel és külön state kezelés használatával.

### Végső döntés
A projekt egy egyszerűbb **Activity + DAO + Room** alapú architektúrát használ.

### Indoklás
- kisebb projektméret
- gyorsabb implementáció
- oktatási célra egyszerűbb
- a jelenlegi funkcionalitás mellett jól kezelhető

### Döntés típusa
**Részben elfogadott, egyszerűsítve**

---

## 3. Admin felhasználó kezelése

### AI javaslat
Az AI több megoldást is javasolt:
- admin kód regisztrációkor
- külön admin backend logika
- automatikus admin felhasználó létrehozása

### Végső döntés
A projekt az **alkalmazás indulásakor automatikusan létrehozott admin felhasználót** használja.

### Indoklás
- egyszerű tesztelhetőség
- a javító könnyen be tud lépni admin felülettel
- nem kell külön admin regisztrációs logikát kezelni

### Döntés típusa
**Elfogadott, alternatívák mérlegelése után**

---

## 4. Lista funkciók kialakítása

### AI javaslat
Az AI javasolta, hogy az óraállások listájában legyen:
- keresés
- szűrés
- rendezés

### Végső döntés
Mindhárom funkció implementálásra került a `ReadingListActivity` képernyőn.

### Indoklás
- megfelel a kiírás UX követelményeinek
- valódi felhasználói értéket ad
- az adatok visszakereshetősége jelentősen javul

### Döntés típusa
**Elfogadott**

---

## 5. Deep link támogatás

### AI javaslat
Az AI javasolta deep link bevezetését az alkalmazás egyik képernyőjére.

### Végső döntés
A projekt támogatja a következő deep linket:

`gazora://readings`

Ez közvetlenül az óraállások listázó képernyőjét nyitja meg.

### Indoklás
- teljesíti a kiírás követelményét
- jól demonstrálja az Android intent-filter rendszer használatát
- hasznos navigációs kiegészítés

### Döntés típusa
**Elfogadott**

---

## 6. Tesztelési stratégia

### AI javaslat
Az AI javasolta:
- entity alapú unit tesztek
- logikai unit tesztek
- Room alapú integrációs teszt

### Végső döntés
A projektben:
- **10 unit teszt**
- **1 integrációs teszt**
készült.

### Indoklás
- megfelel a kiírásnak
- jól ellenőrizhető vele az adatmodell és az alap üzleti logika
- a Room adatbázis együttműködése is tesztelhető

### Döntés típusa
**Elfogadott**

---

## 7. Óraállás és mérő összekapcsolása

### AI javaslat
Az AI javasolta, hogy az óraállások ne fix azonosítóval legyenek mentve, hanem a felhasználó válassza ki a konkrét mérőt.

### Végső döntés
A projektben a `NewReadingActivity` képernyőn **Spinner segítségével választható ki a mérő**, és az óraállás a kiválasztott `meterId`-hoz kerül mentésre.

### Indoklás
- helyesebb adatmodell
- valós kapcsolat a `Meter` és `Reading` entitás között
- későbbi szűrés és listázás is pontosabb

### Döntés típusa
**Elfogadott**

---

## Összegzés

Az AI több fontos fejlesztési irányt és technikai javaslatot adott, de minden esetben fejlesztői döntés született arról, hogy mely megoldások kerülnek be a projektbe. Az AI így a fejlesztést támogató eszközként működött, nem automatikus megoldásként.