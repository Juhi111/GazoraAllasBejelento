# COMPONENTS.md

## Komponens- és képernyőstruktúra

Az alkalmazás több különálló Activity-ből és adatkezelő komponensből épül fel.  
A navigáció Intent alapú, a képernyők funkcionális egységek szerint vannak elkülönítve.

---

# Fő komponenshierarchia

```text
Application
 └── LoginActivity
      ├── RegisterActivity
      └── DashboardActivity
           ├── NewReadingActivity
           ├── ReadingListActivity
           │    └── ReadingDetailActivity
           ├── ReminderActivity
           └── AdminActivity
                ├── PropertyActivity
                └── MeterActivity