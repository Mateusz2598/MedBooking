BookingMed
===

Aplikacja służy do zarządzania prywatną opieką medyczną. Można wykonywać operacje CRUD oraz inne zapytania np.

- symulacja kupowania recepty w aptece,
- liczenie sumy zarobionych pieniędzy z odpłatnych wizyt oraz konsultacji telefonicznych dla wszystkich lekarzy,
- symulacja odnajdywania pacjenta w placówce medycznej po peselu,
- aktualizowanie danych pacjenta takich jak nr. phone, mail i adres,
- zwrócenie wszystkich specjalizacji lekarskich oferowanych przez placówkę,
- zatrudnianie i zwalnianie lekarzy z placówek,
- rezerwowanie i odwoływanie umówionych wizyt oraz konsultacji lekarskich,
- usuwanie wszystkich wizyt lub konsultacji lekarskich dla konkretnego doktora w danym dniu,
- symulacja pobrania wszystkich umówionych pacjentów w danym dniu dla konkretnego lekarza.

#  Użyte technologie:
- Spring boot,
- Spring data,
- Hibernate
- użyta baza danych MySQL.

#  Instrukcja:
- zmodyfikuj application.properties i połącz się z bazą danych,
- wczytaj dane startowe do bazy danych z pliku (insert-values-db.sql) dołączonego do projektu,
- zaimportuj zapytania z pliku (MedBooking_query_postman).

### Wartości unikalne w kolumnach:

- Patient: pesel, numerPhone, mail,
- Perscription: code,
- Medicin: name,
- Doctor: employeNumber,
- LabAsistant: employeNumber.

#  Dodatkowe pliki:
- schemat bazy danych
- insert-values-db.sql
- MedBooking_query_postman

![obraz](https://tiny.pl/cg4k9)

## Entities :
#### 1. Address,
#### 2. ConsultationsPhone,
#### 3. Doctor,
#### 4. Facility,
#### 5. LabAssistant,
#### 6. LaboratoryTest,
#### 7. Medicin,
#### 8. Patient,
#### 9. Prescription,
#### 10. Skills,
#### 11. StationaryVisit.


### Wybrane endpointy z pominięciem operacji CRUD do uruchamiania metod wraz z opisami, znajdują się poniżej:

### * Prescription
#### 1. getPrescriptionPharmacy
```
Http request in Postman : 
@GetMapping     http://localhost:8080/prescription/forPharmacy

Postman default body: 
{
    "code": "3698",
    "pesel":"51060572242"
}
Example response:
- 2569, 75120622133,
- 3625, 96080656714,
- 9758, 52062048225,
- 3698, 51060572242,
- 1478, 67061924445,
- 2587, 74073089893,
- 3214, 59071325693,
- 4123, 82011032427,
- 1958, 73071273497,
- 3749, 72122288488,
- 6839, 86072611166.

```
### * Doctor
#### 1. salaryAllDoctorsFromVisits
Metoda liczy sumę zarobionych pieniędzy z odpłatnych wizyt dla wszystkich lekarzy i zwraca mapę.
````
Http request in Postman : 
@GetMapping      http://localhost:8080/doctor/salaryAllVisits

Params:
dateFrom - 2022-02-03
dateTo - 2026-10-03
````
#### 2. salaryAllDoctorsFromConsultation
Metoda liczy sumę zarobionych pieniędzy z odpłatnych konsultacji telefonicznych dla wszystkich lekarzy 
i zwraca mapę.
```
Http request in Postman : 
@GetMapping     http://localhost:8080/doctor/salaryAllConsultation

Params:
dateFrom - 2022-02-03
dateTo - 2026-10-03
```
#### 3. addFacilityToDoctor
Z uwagi na fakt, iż lekarze pracują zazwyczaj w kilku placówkach metoda dodaje do lekarza kolejną placówkę.
```
Http request in Postman : 
@PatchMapping     http://localhost:8080/doctor/addFacility

Params:
doctorId - 5
facilityId - 1
```
#### 4. deleteFacilityToDoctor
Lekarz może się zwolnić z jakiejś placówki dlatego metoda usuwa z doktora daną placówkę.
```
Http request in Postman : 
@PatchMapping     http://localhost:8080/doctor/deleteFacility

Params:
doctorId - 5
facilityId - 1

```
### * Patient
#### 1. getPatientByPesel
Metoda symuluje sytuacje, kiedy przychodząc na umówioną wizytę do placówki podajemy Pani w recepcji swój 
pesel by recepcjonistka mogła nam wskazać pokój, pod który mamy się udać. 
```
Http request in Postman : 
@GetMapping     http://localhost:8080/patient/pesel

Postman default body: 
{
    "pesel":"74073089893"
}
Other valid combinations:
- 75120622133,
- 96080656714,
- 52062048225,
- 51060572242,
- 67061924445,
- 74073089893.

```
#### 2. updateByPhoneMailAddress
Metoda aktualizuje dane pacjenta takie jak nr. telefonu, e-mail oraz adres.
```
Http request in Postman : 
@PatchMapping     http://localhost:8080/patient

Params:
patientId - 10
Postman default body: 
{
  "numberPhone": "+48693259885",
  "mail": "xayillubreve-5751@yopmail.com",
    "addressDto": {
     "city": "Poznan",
     "street": "Inwalidow",
     "zipCode": "11-113",
     "numberStreet": 20,
     "numberFlat": "96"
  }
}

```
### * Facility
#### 1. specjalizationFacility
Metoda zwraca zbiór wszystkich specjalizacji lekarskich oferowanych przez daną placówkę.
```
Http request in Postman : 
@GetMapping     http://localhost:8080/facility/specjalization

Params:
facilityId - 2

```
### * StationaryVisit 
#### 1. findAllAppointmentsPatientToVisit
Kiedy Doktor przychodzi do pracy potrzebuje listy wszystkich pacjentów, z którymi jest 
dziś umówiony, chociażby dlatego, żeby zaprosić odpowiedniego pacjenta do gabinetu z korytarza.
Metoda przyjmuje ID doktora oraz datę i zwraca wszystkich umówionych pacjentów w tym dniu.
```
Http request in Postman : 
@GetMapping     http://localhost:8080/stationaryVisit/allAppointments

Params:
doctorId - 14
localDate - 2023-03-26

```
#### 2. visitBooking
Metoda rezerwuje wizytę lekarską. Operuje na statusem wyróżniamy takie statusy jak AVAILABLE, NOAVAILABLE.
```
Http request in Postman : 
@PatchMapping     http://localhost:8080/stationaryVisit/booking

Params:
patientId - 2
visitId - 9

```
#### 3. cancellationVisit
Metoda odwołuje rezerwacje wizyty lekarskiej.
```
Http request in Postman : 
@PatchMapping     http://localhost:8080/stationaryVisit/cancellation

Params:
visitId - 8

```
#### 4. deleteVisitByDoctorIdAndDay
Kiedy wiadomo, że doktor nie zjawi się w pracy w danym dniu trzeba odwołać wszystkie wizyty, które
doktor ma w ten dzień. Metoda usuwa wszystkie wizyty w danym dniu dla konkretnego doktora
i zwraca listę danych kontaktowych pacjentów, którym odwołano wizyte by móc się z nim skontaktować
w celu poinformowania o zaistniałej sytuacji oraz umówienia innego terminu.
Jeśli nie było umówionych pacjentów zwraca null.
```
Http request in Postman : 
@DeleteMapping    http://localhost:8080/stationaryVisit/deleteAllOnDay

Params:
doctorId - 14
localDate - 2023-03-26

```
### * ConsultationsPhone
#### 1. findAllAppointmentsPatients
Kiedy Doktor przychodzi do pracy potrzebuje listy wszystkich pacjentów, do których ma tego dnia zadzwonić.
Metoda przyjmuje ID doktora oraz datę i zwraca wszystkich umówionych pacjentów 
oraz dane kontaktowe, by móc się z nimi skontaktować.
```
Http request in Postman : 
@GetMapping     http://localhost:8080/consultations/all/appointments

Params:
doctorId - 1
localDate - 2023-03-26

```
#### 2. consultationBooking
Metoda rezerwuje konsultację telefoniczną. Operuje na statusem wyróżniamy takie statusy jak AVAILABLE, NOAVAILABLE.
```
Http request in Postman : 
@PatchMapping     http://localhost:8080/consultations/booking

Params:
patientId - 15
consultationId - 7

```
#### 3. cancellationBooking
Metoda odwołuje konsultację telefoniczną.
```
Http request in Postman : 
@PatchMapping     http://localhost:8080/consultations/cancellation

Params:
consultationId - 8

```
#### 4. deletedConsultationsByDoctorIdAndDay
Kiedy wiadomo, że doktor nie zjawi się w pracy w danym dniu trzeba odwołać wszystkie konsultacje, które 
doktor ma w ten dzień. Metoda usuwa wszystkie konsultacje w danym dniu dla konkretnego doktora
i zwraca listę danych kontaktowych pacjentów, którym odwołano wizyte by móc się z nim skontaktować 
w celu poinformowania o zaistniałej sytuacji oraz umówienia innego terminu. 
Jeśli nie było umówionych pacjentów zwraca null.
```
Http request in Postman : 
@DeleteMapping     http://localhost:8080/consultations/deleteAllOnDay

Params:
doctorId - 1
localDate - 2023-03-26

