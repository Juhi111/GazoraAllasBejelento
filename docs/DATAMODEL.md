# DATAMODEL.md

## Adatmodell áttekintés

Az alkalmazás öt fő entitást használ:

1. `User`
2. `Property`
3. `Meter`
4. `Reading`
5. `Reminder`

Az entitások célja a felhasználók, fogyasztási helyek, mérők, óraállások és emlékeztetők tárolása.

---

## 1. User

A rendszer felhasználóit reprezentálja.

### Mezők

| Mező neve | Típus | Leírás |
|---|---|---|
| `id` | `int` | Egyedi azonosító |
| `name` | `String` | Felhasználó neve |
| `email` | `String` | Bejelentkezéshez használt email cím |
| `password` | `String` | Felhasználó jelszava |
| `role` | `String` | Jogosultsági szerepkör (`USER` vagy `ADMIN`) |

### Funkció
- bejelentkezés
- jogosultságkezelés
- admin és normál felhasználó elkülönítése

---

## 2. Property

A fogyasztási helyeket reprezentálja.

### Mezők

| Mező neve | Típus | Leírás |
|---|---|---|
| `id` | `int` | Egyedi azonosító |
| `userId` | `int` | A felhasználó azonosítója, akihez a fogyasztási hely tartozik |
| `name` | `String` | Fogyasztási hely neve |
| `address` | `String` | Fogyasztási hely címe |

### Funkció
- fogyasztási helyek nyilvántartása
- mérők logikai csoportosítása

---

## 3. Meter

A gázmérőket reprezentálja.

### Mezők

| Mező neve | Típus | Leírás |
|---|---|---|
| `id` | `int` | Egyedi azonosító |
| `propertyId` | `int` | A kapcsolódó fogyasztási hely azonosítója |
| `meterNumber` | `String` | A mérő gyári száma |
| `type` | `String` | A mérő típusa, jelenleg `gas` |

### Funkció
- mérők nyilvántartása
- óraállások összekapcsolása konkrét mérőkkel

---

## 4. Reading

A rögzített óraállásokat reprezentálja.

### Mezők

| Mező neve | Típus | Leírás |
|---|---|---|
| `id` | `int` | Egyedi azonosító |
| `meterId` | `int` | A kapcsolódó mérő azonosítója |
| `date` | `String` | Az óraállás rögzítésének dátuma |
| `value` | `int` | Az óraállás értéke |
| `note` | `String` | Opcionális megjegyzés |

### Funkció
- mérőállások rögzítése
- előzmények kezelése
- keresés, szűrés, rendezés alapja

---

## 5. Reminder

Az emlékeztetőket reprezentálja.

### Mezők

| Mező neve | Típus | Leírás |
|---|---|---|
| `id` | `int` | Egyedi azonosító |
| `title` | `String` | Emlékeztető címe |
| `date` | `String` | Emlékeztető dátuma |

### Funkció
- diktálási vagy egyéb feladat emlékeztetése
- felhasználó által kezelt időzített bejegyzések

---

# Logikai kapcsolatok

## User – Property
Kapcsolat típusa:


1 : N