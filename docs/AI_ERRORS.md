# AI_ERRORS.md

## AI hibák és korrekciók

A projekt során az AI több hasznos javaslatot adott, ugyanakkor néhány esetben hibás vagy hiányos megoldás is született. Ezeket fejlesztői ellenőrzéssel és javítással kellett korrigálni.

---

## 1. DAO teszt futtatása sima unit tesztként

### AI javaslat
Az AI kezdetben olyan DAO tesztet javasolt, amely sima unit tesztként futott volna a `test` mappában.

### Mi volt a probléma?
A Room adatbázis működéséhez Android környezet szükséges, ezért a DAO teszt nem volt megfelelő sima JVM unit tesztként.

### Javítás
A megoldás az lett, hogy az adatbázist használó teszt **instrumented / integration test** formában, az `androidTest` mappában készült el, in-memory Room adatbázissal.

### Tanulság
Nem minden teszt futtatható tisztán unit tesztként, ha Android framework vagy Room környezet is szükséges.

---

## 2. Deep link konfiguráció hiányossága

### AI javaslat
Az AI első deep link példája nem tartalmazott minden szükséges elemet az AndroidManifest konfigurációban.

### Mi volt a probléma?
A deep link inicializálásából hiányzott a megfelelő böngésző-kompatibilis intent kezeléshez szükséges kategória, ezért a megoldás nem volt teljes.

### Javítás
Az intent-filter kiegészült a szükséges elemekkel, különösen a következő kategóriával:

- `android.intent.category.BROWSABLE`

Így a deep link végül megfelelően működött.

### Tanulság
Manifest konfigurációknál az AI által adott első minta gyakran ellenőrzést és finomítást igényel.

---

## 3. Reminder DatePicker bekötési hiba

### AI javaslat
Az AI javasolta a `DatePicker` bekötését a Reminder képernyő dátum mezőjére.

### Mi volt a probléma?
A `setOnClickListener()` hívás a `findViewById()` előtt került be a kódba, ezért a `reminderDateInput` még `null` volt, és az alkalmazás összeomlott.

### Javítás
A helyes sorrend:
1. `setContentView(...)`
2. `findViewById(...)`
3. `setOnClickListener(...)`

A listener végül a komponens inicializálása után került bekötésre.

### Tanulság
Az AI által javasolt kódot mindig ellenőrizni kell az Android Activity életciklus és inicializálási sorrend szempontjából.

---

## 4. Room sémafrissítés és verziókezelés

### AI javaslat
A `User` entitás bővítése után az AI részben helyesen jelezte, hogy a Room adatbázis sémája megváltozott.

### Mi volt a probléma?
Az adatbázis összeomlott bejelentkezéskor, mert a Room séma megváltozott, de az adatbázis verziója nem lett növelve.

### Javítás
- az `AppDatabase` verziója növelésre került
- `fallbackToDestructiveMigration()` került a builderbe
- az alkalmazás újratelepítése után a probléma megszűnt

### Tanulság
A Room séma módosításánál nem elég csak az entitást átírni, az adatbázis verziókezelését is követni kell.

---

## Összegzés

Az AI hasznos támogatást adott a projekt során, de több esetben is szükség volt:
- hibák felismerésére
- manuális korrekcióra
- fejlesztői ellenőrzésre

Ez megerősítette, hogy az AI segítség önmagában nem helyettesíti a fejlesztői döntéshozatalt és hibakeresést.