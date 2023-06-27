package com.example.demo.mappers;

import com.example.demo.dtos.*;
import com.example.demo.entities.*;
import com.example.demo.response.*;

import java.util.stream.Collectors;

public interface Mappers {

    default Address mapperAddressDtoToEntity(AddressDto addressDto) {
        return addressDto == null ? null : Address.builder()
                .id(addressDto.getId())
                .city(addressDto.getCity())
                .street(addressDto.getStreet())
                .zipCode(addressDto.getZipCode())
                .numberStreet(addressDto.getNumberStreet())
                .numberFlat(addressDto.getNumberFlat())
                .build();
    }

    default AddressDto mapperAddressEntityToDto(Address address) {
        return address == null ? null : AddressDto.builder()
                .id(address.getId())
                .city(address.getCity())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .numberStreet(address.getNumberStreet())
                .numberFlat(address.getNumberFlat())
                .build();
    }

    default ConsultationsPhone mapperConsultationsDtoToEntity(ConsultationsPhoneDto consultationsDto) {
        return consultationsDto == null ? null : ConsultationsPhone.builder()
                .id(consultationsDto.getId())
                .status(consultationsDto.getStatus())
                .specialization(consultationsDto.getSpecialization())
                .date(consultationsDto.getDate())
                .time(consultationsDto.getTime())
                .price(consultationsDto.getPrice())
                .recommendations(consultationsDto.getRecommendations())
                .doctorId(consultationsDto.getDoctorId())
                .patientId(consultationsDto.getPatientId())
                .build();
    }

    default ConsultationsPhoneDto mapperConsultationsEntityToDto(ConsultationsPhone consultations) {
        return consultations == null ? null : ConsultationsPhoneDto.builder()
                .id(consultations.getId())
                .status(consultations.getStatus())
                .specialization(consultations.getSpecialization())
                .date(consultations.getDate())
                .time(consultations.getTime())
                .price(consultations.getPrice())
                .recommendations(consultations.getRecommendations())
                .doctorId(consultations.getDoctorId())
                .patientId(consultations.getPatientId())
                .build();
    }

    default Doctor mapperDoctorDtoToEntity(DoctorDto doctorDto) {
        return doctorDto == null ? null : Doctor.builder()
                .id(doctorDto.getId())
                .name(doctorDto.getName())
                .surname(doctorDto.getSurname())
                .employeeNumber(doctorDto.getEmployeeNumber())
                .stationaryVisit(doctorDto.getStationaryVisit().stream()
                        .map(o -> mapperVisitDtoToEntity(o))
                        .collect(Collectors.toList()))
                .eConsultations(doctorDto.getEConsultations().stream()
                        .map(o -> mapperConsultationsDtoToEntity(o))
                        .collect(Collectors.toList()))
                .skills(mapperSkillsDtoToEntity(doctorDto.getSkills()))
                .address(mapperAddressDtoToEntity(doctorDto.getAddress()))
                .facilitySet(doctorDto.getFacility().stream()
                        .map(o -> mapperFacilityDtoToEntity(o))
                        .collect(Collectors.toList()))
                .build();
    }

    default DoctorDto mapperDoctorEntityToDto(Doctor doctor) {
        return doctor == null ? null : DoctorDto.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .employeeNumber(doctor.getEmployeeNumber())
                .stationaryVisit(doctor.getStationaryVisit() == null ? null : doctor.getStationaryVisit().stream()
                        .map(o -> mapperVisitEntityToDto(o))
                        .collect(Collectors.toSet()))
                .eConsultations(doctor.getEConsultations() == null ? null : doctor.getEConsultations().stream()
                        .map(o -> mapperConsultationsEntityToDto(o))
                        .collect(Collectors.toSet()))
                .skills(mapperSkillsEntityToDto(doctor.getSkills()))
                .address(mapperAddressEntityToDto(doctor.getAddress()))
                .facility(doctor.getFacilitySet().stream()
                        .map(o -> mapperFacilityEntityToDto(o))
                        .collect(Collectors.toSet()))
                .build();
    }

    default DoctorResponse mapperDoctorResponse(Doctor doctor) {
        return doctor == null ? null : DoctorResponse.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .employeeNumber(doctor.getEmployeeNumber())
                .specialization(doctor.getSkills().getSpecialization())
                .addressId(doctor.getAddress().getId())
                .facilityId(doctor.getFacilitySet().stream()
                        .map(o -> o.getId())
                        .collect(Collectors.toList()))
                .build();
    }

    default Facility mapperFacilityDtoToEntity(FacilityDto facilityDto) {
        return facilityDto == null ? null : Facility.builder()
                .id(facilityDto.getId())
                .name(facilityDto.getName())
                .address(facilityDto.getAddress())
                .stationaryVisit(facilityDto.getStationaryVisit() == null ? null : facilityDto.getStationaryVisit().stream().
                        map(o -> mapperVisitDtoToEntity(o))
                        .toList())
                .doctorSet(facilityDto.getDoctorSet() == null ? null : facilityDto.getDoctorSet().stream()
                        .map(o-> mapperDoctorDtoToEntity(o))
                        .toList())
                .labAssistants(facilityDto.getLabAssistants() == null ? null : facilityDto.getLabAssistants().stream()
                        .map(o-> mapperLabAssistantDtoToEntity(o))
                        .toList())
                .build();
    }

    default FacilityDto mapperFacilityEntityToDto(Facility facility) {
        return facility == null ? null : FacilityDto.builder()
                .id(facility.getId())
                .name(facility.getName())
                .address(facility.getAddress())
                .stationaryVisit(facility.getStationaryVisit().stream()
                        .map(o-> mapperVisitEntityToDto(o))
                        .collect(Collectors.toSet()))
                .doctorSet(facility.getDoctorSet().stream()
                        .map(o-> mapperDoctorEntityToDto(o))
                        .collect(Collectors.toSet()))
                .labAssistants(facility.getLabAssistants().stream()
                        .map(o-> mapperLabAssistantEntityToDto(o))
                        .collect(Collectors.toSet()))
                .build();
    }

    default FacilityResponse mapperFacilityResponse(Facility facility) {
        return facility == null ? null : FacilityResponse.builder()
                .id(facility.getId())
                .name(facility.getName())
                .address(facility.getAddress())
                .build();
    }

    default LabAssistant mapperLabAssistantDtoToEntity(LabAssistantDto labAssistantDto) {
        return labAssistantDto == null ? null : LabAssistant.builder()
                .id(labAssistantDto.getId())
                .name(labAssistantDto.getName())
                .surname(labAssistantDto.getSurname())
                .employeeNumber(labAssistantDto.getEmployeeNumber())
                .facility(mapperFacilityDtoToEntity(labAssistantDto.getFacility()))
                .build();
    }

    default LabAssistantDto mapperLabAssistantEntityToDto(LabAssistant labAssistant) {
        return labAssistant == null ? null : LabAssistantDto.builder()
                .id(labAssistant.getId())
                .name(labAssistant.getName())
                .surname(labAssistant.getSurname())
                .employeeNumber(labAssistant.getEmployeeNumber())
                .listIdLaboratoryTest(labAssistant.getLaboratoryTest().stream()
                        .map(o -> o.getId())
                        .collect(Collectors.toSet()))
                .facility(mapperFacilityEntityToDto(labAssistant.getFacility()))
                .build();
    }

    default LabAssistantResponse mapperLabAssistantResponse(LabAssistant labAssistant) {
        return labAssistant == null ? null : LabAssistantResponse.builder()
                .id(labAssistant.getId())
                .name(labAssistant.getName())
                .surname(labAssistant.getSurname())
                .employeeNumber(labAssistant.getEmployeeNumber())
                .facilityId(labAssistant.getFacility().getId())
                .build();
    }

    default LaboratoryTest mapperLaboratoryTestDtoToEntity(LaboratoryTestDto laboratoryTestDto) {
        return laboratoryTestDto == null ? null : LaboratoryTest.builder()
                .id(laboratoryTestDto.getId())
                .status(laboratoryTestDto.getStatus())
                .setLaboratoryTest(laboratoryTestDto.getSetLaboratoryTest())
                .recommendations(laboratoryTestDto.getRecommendations())
                .dateTime(laboratoryTestDto.getDateTime())
                .price(laboratoryTestDto.getPrice())
                .facility(mapperFacilityDtoToEntity(laboratoryTestDto.getFacility()))
                .patientId(laboratoryTestDto.getPatientId())
                .doctorId(laboratoryTestDto.getDoctorId())
                .labAssistantId(laboratoryTestDto.getLabAssistantId())
                .build();
    }

    default LaboratoryTestResponse mapperLaboratoryTestResponse(LaboratoryTest testEntiti) {
        return testEntiti == null ? null : LaboratoryTestResponse.builder()
                .id(testEntiti.getId())
                .status(testEntiti.getStatus())
                .setLaboratoryTest(testEntiti.getSetLaboratoryTest())
                .recommendations(testEntiti.getRecommendations())
                .dateTime(testEntiti.getDateTime())
                .price(testEntiti.getPrice())
                .facility(mapperFacilityResponse(testEntiti.getFacility()))
                .patientId(testEntiti.getPatientId())
                .doctorId(testEntiti.getDoctorId())
                .labAssistantId(testEntiti.getLabAssistantId())
                .build();
    }

    default Medicin mapperMedicinDtoToEntity(MedicinDto medicinDto) {
        return medicinDto == null ? null : Medicin.builder()
                .id(medicinDto.getId())
                .name(medicinDto.getName())
                .dose(medicinDto.getDose())
                .quantity(medicinDto.getQuantity())
                .prescription(medicinDto.getIdPrescription() == null ? null : medicinDto.getIdPrescription().stream()
                        .map(o -> Prescription.builder().id(o).build())
                        .collect(Collectors.toList()))
                .build();
    }

    default MedicinDto mapperMedicinEntityToDto(Medicin medicin) {
        return medicin == null ? null : MedicinDto.builder()
                .id(medicin.getId())
                .name(medicin.getName())
                .dose(medicin.getDose())
                .quantity(medicin.getQuantity())
                .idPrescription(medicin.getPrescription() == null ? null : medicin.getPrescription().stream()
                        .map(o -> o.getId())
                        .toList())
                .build();
    }

    default MedicinResponse mapperMedicinResponse(Medicin medicin) {
        return medicin == null ? null : MedicinResponse.builder()
                .name(medicin.getName())
                .dose(medicin.getDose())
                .quantity(medicin.getQuantity())
                .build();
    }

    default Patient mapperPatientDtoToEntity(PatientDto patientDto) {
        return patientDto == null ? null : Patient.builder()
                .id(patientDto.getId())
                .name(patientDto.getName())
                .surname(patientDto.getSurname())
                .pesel(patientDto.getPesel())
                .numberPhone(patientDto.getNumberPhone())
                .mail(patientDto.getMail())
                .address(mapperAddressDtoToEntity(patientDto.getAddress()))
                .build();
    }

    default PatientDto mapperPatientEntityToDto(Patient patient) {
        return patient == null ? null :PatientDto.builder()
                .id(patient.getId())
                .name(patient.getName())
                .surname(patient.getSurname())
                .pesel(patient.getPesel())
                .numberPhone(patient.getNumberPhone())
                .mail(patient.getMail())
                .address(mapperAddressEntityToDto(patient.getAddress()))
                .setIdConsultations(patient.getConsultations().stream()
                        .map(o -> o.getId())
                        .collect(Collectors.toList()))
                .build();
    }

    default PatientResponse mapperPatientToResponse(Patient patient) {
        return patient == null ? null :PatientResponse.builder()
                .id(patient.getId())
                .name(patient.getName())
                .surname(patient.getSurname())
                .pesel(patient.getPesel())
                .numberPhone(patient.getNumberPhone())
                .mail(patient.getMail())
                .addressId(patient.getAddress().getId())
                .build();
    }

    default PatientUpdateResponse mapperPatientUpdateResponse(Patient patient) {
        return patient == null ? null :PatientUpdateResponse.builder()
                .id(patient.getId())
                .name(patient.getName())
                .surname(patient.getSurname())
                .pesel(patient.getPesel())
                .numberPhone(patient.getNumberPhone())
                .mail(patient.getMail())
                .addressDto(mapperAddressEntityToDto(patient.getAddress()))
                .build();
    }

    default PatientVisitAndConsultationResponse mapperPatientToVisitCanceled(Patient patient) {
        return patient == null ? null : PatientVisitAndConsultationResponse.builder()
                .id(patient.getId())
                .name(patient.getName())
                .surname(patient.getSurname())
                .numberPhone(patient.getNumberPhone())
                .mail(patient.getMail())
                .build();
    }

    default Prescription mapperPrescriptionDtoToEntity(PrescriptionDto prescriptionDto) {
        return prescriptionDto == null ? null : Prescription.builder()
                .id(prescriptionDto.getId())
                .code(prescriptionDto.getCode())
                .issureDate(prescriptionDto.getIssureDate())
                .expirationDate(prescriptionDto.getExpirationDate())
                .doctorId(prescriptionDto.getDoctorId())
                .patientId(prescriptionDto.getPatientId())
                .medicin(prescriptionDto.getMedicin() == null ? null : prescriptionDto.getMedicin().stream()
                        .map(o -> Medicin.builder().id(o).build())
                        .collect(Collectors.toList()))
                .build();
    }

    default PrescriptionDto mapperPrescriptionEntityToDto(Prescription prescription) {
        return prescription == null ? null : PrescriptionDto.builder()
                .id(prescription.getId())
                .code(prescription.getCode())
                .issureDate(prescription.getIssureDate())
                .expirationDate(prescription.getExpirationDate())
                .doctorId(prescription.getDoctorId())
                .patientId(prescription.getPatientId())
                .medicin(prescription.getMedicin() == null ? null : prescription.getMedicin().stream()
                        .map(o -> o.getId())
                        .collect(Collectors.toSet()))
                .build();
    }

    default Skills mapperSkillsDtoToEntity(SkillsDto skillsDto) {
        return skillsDto == null ? null : Skills.builder()
                .id(skillsDto.getId())
                .specialization(skillsDto.getSpecialization())
                .medicalExaminationEnums(skillsDto.getMedicalExaminationEnums())
                .build();
    }

    default SkillsDto mapperSkillsEntityToDto(Skills skills) {
        return skills == null ? null : SkillsDto.builder()
                .id(skills.getId())
                .specialization(skills.getSpecialization())
                .medicalExaminationEnums(skills.getMedicalExaminationEnums())
                .build();
    }

    default StationaryVisit mapperVisitDtoToEntity(StationaryVisitDto visitDto) {
        return visitDto == null ? null : StationaryVisit.builder()
                .id(visitDto.getId())
                .status(visitDto.getStatus())
                .specialization(visitDto.getSpecialization())
                .date(visitDto.getDate())
                .time(visitDto.getTime())
                .price(visitDto.getPrice())
                .recommendations(visitDto.getRecommendations())
                .doctorId(visitDto.getDoctorId())
                .patientId(visitDto.getPatientId())
                .facility(mapperFacilityDtoToEntity(visitDto.getFacility()))
                .build();
    }

    default StationaryVisitDto mapperVisitEntityToDto(StationaryVisit visit) {
        return visit == null ? null : StationaryVisitDto.builder()
                .id(visit.getId())
                .status(visit.getStatus())
                .specialization(visit.getSpecialization())
                .date(visit.getDate())
                .time(visit.getTime())
                .price(visit.getPrice())
                .recommendations(visit.getRecommendations())
                .doctorId(visit.getDoctorId())
                .patientId(visit.getPatientId())
                .facility(mapperFacilityEntityToDto(visit.getFacility()))
                .build();
    }

    default StationaryVisitResponse mapperVisitResponse(StationaryVisit visit) {
        return visit == null ? null : StationaryVisitResponse.builder()
                .id(visit.getId())
                .status(visit.getStatus())
                .specialization(visit.getSpecialization())
                .date(visit.getDate())
                .time(visit.getTime())
                .price(visit.getPrice())
                .recommendations(visit.getRecommendations())
                .doctorId(visit.getDoctorId())
                .patientId(visit.getPatientId())
                .facility(mapperFacilityResponse(visit.getFacility()))
                .build();
    }
}