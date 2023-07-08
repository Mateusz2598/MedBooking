BookingMed
===

The application is used to manage private medical care. You can perform CRUD operations and other queries, e. g.

- simulation of buying a prescription in a pharmacy,
- calculation of income from medical visits and telephone consultations,
- searching for patients by PESEL number,
- updating data (phone number, e-mail and address) of patients,
- download a collection of all medical specialties offered by the institution,
- recruitment and dismissal of doctors from establishments,
- booking and cancellation of medical appointments,
- deletion of all medical appointments or consultations to a particular doctor on a given day,
- download the list of patients scheduled for a given day with a given doctor.

#  Technologies used:
- Spring boot,
- Spring data,
- Hibernate,
- MySQL database used.

#  Instruction:
- modify application.properties and connect to database,
- load the startup data into the database from the file (insert-values-db.sql) attached to the project,
- import queries from file (MedBooking_query_postman).

### Unique values in columns:

- Patient: pesel, numerPhone, mail,
- Perscription: code,
- Medicin: name,
- Doctor: employeNumber,
- LabAsistant: employeNumber.

#  Additional Files:
- database schema,
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


### Selected endpoints with omission of CRUD operations to run methods with descriptions are below:

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
The method counts the sum of money earned from paid visits for all doctors and returns the map.
````
Http request in Postman : 
@GetMapping      http://localhost:8080/doctor/salaryAllVisits

Params:
dateFrom - 2022-02-03
dateTo - 2026-10-03
````
#### 2. salaryAllDoctorsFromConsultation
The method counts the sum of money earned from paid telephone consultations for all doctors
and returns the map.
```
Http request in Postman : 
@GetMapping     http://localhost:8080/doctor/salaryAllConsultation

Params:
dateFrom - 2022-02-03
dateTo - 2026-10-03
```
#### 3. addFacilityToDoctor
Due to the fact that doctors usually work in several facilities, the method adds another facility to the doctor.
```
Http request in Postman : 
@PatchMapping     http://localhost:8080/doctor/addFacility

Params:
doctorId - 5
facilityId - 1
```
#### 4. deleteFacilityToDoctor
The doctor may be discharged from an institution, so the method removes the institution from the doctor.
```
Http request in Postman : 
@PatchMapping     http://localhost:8080/doctor/deleteFacility

Params:
doctorId - 5
facilityId - 1

```
### * Patient
#### 1. getPatientByPesel
The method simulates situations when coming for an appointment to the facility we give you your
so that the receptionist could point us to the room we should go to. 
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
The method updates patient information such as phone number, e-mail and address.
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
The method returns a set of all medical specialties offered by a given facility.
```
Http request in Postman : 
@GetMapping     http://localhost:8080/facility/specjalization

Params:
facilityId - 2

```
### * StationaryVisit 
#### 1. findAllAppointmentsPatientToVisit
When the Doctor comes to work he needs a list of all the patients he's with.
I had an appointment today, if only to invite the right patient into the office down the hall.
The method takes the doctor's ID and date and returns all scheduled patients on that date.
```
Http request in Postman : 
@GetMapping     http://localhost:8080/stationaryVisit/allAppointments

Params:
doctorId - 14
localDate - 2023-03-26

```
#### 2. visitBooking
The method reserves a doctor's appointment. He's operating on status. We distinguish such statuses as AVAILABLE, NOAVAILABLE.
```
Http request in Postman : 
@PatchMapping     http://localhost:8080/stationaryVisit/booking

Params:
patientId - 2
visitId - 9

```
#### 3. cancellationVisit
The method cancels appointments.
```
Http request in Postman : 
@PatchMapping     http://localhost:8080/stationaryVisit/cancellation

Params:
visitId - 8

```
#### 4. deleteVisitByDoctorIdAndDay
When it is known that the doctor will not show up for work on a given day, you have to cancel all appointments that
The doctor has that day. The method removes all visits on a given day for a specific doctor
and returns a list of the contact details of patients whose appointments have been cancelled in order to be able to contact them
to inform you about the situation and to arrange another date.
If there were no scheduled patients returns null.
```
Http request in Postman : 
@DeleteMapping    http://localhost:8080/stationaryVisit/deleteAllOnDay

Params:
doctorId - 14
localDate - 2023-03-26

```
### * ConsultationsPhone
#### 1. findAllAppointmentsPatients
When the Doctor comes to work, he needs a list of all the patients he's supposed to call that day.
The method takes doctor ID and date and returns all scheduled patients 
and contact details to be able to contact them.
```
Http request in Postman : 
@GetMapping     http://localhost:8080/consultations/all/appointments

Params:
doctorId - 1
localDate - 2023-03-26

```
#### 2. consultationBooking
The method reserves a telephone consultation. Operating on the status we distinguish such statuses as AVAILABLE, NOAVAILABLE.
```
Http request in Postman : 
@PatchMapping     http://localhost:8080/consultations/booking

Params:
patientId - 15
consultationId - 7

```
#### 3. cancellationBooking
The method cancels the telephone consultation.
```
Http request in Postman : 
@PatchMapping     http://localhost:8080/consultations/cancellation

Params:
consultationId - 8

```
#### 4. deletedConsultationsByDoctorIdAndDay
When it is known that the doctor will not show up for work on a given day, you have to cancel all consultations that 
The doctor has that day. The method removes all consultations on a given day for a specific doctor
and returns a list of the contact details of patients whose appointments have been cancelled in order to be able to contact them 
to inform you about the situation and to arrange another date. 
If there were no scheduled patients returns null.
```
Http request in Postman : 
@DeleteMapping     http://localhost:8080/consultations/deleteAllOnDay

Params:
doctorId - 1
localDate - 2023-03-26

