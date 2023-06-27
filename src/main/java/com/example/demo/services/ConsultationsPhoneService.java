package com.example.demo.services;

import com.example.demo.dtos.ConsultationsPhoneDto;
import com.example.demo.entities.ConsultationsPhone;
import com.example.demo.enums.Status;
import com.example.demo.exception.MedBookingException;
import com.example.demo.mappers.Mappers;
import com.example.demo.repositories.ConsultationsRepository;
import com.example.demo.repositories.DoctorRepository;
import com.example.demo.repositories.PatientRepository;
import com.example.demo.requests.CreateConsultationsRequest;
import com.example.demo.response.PatientVisitAndConsultationResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultationsPhoneService implements Mappers {

    private final ConsultationsRepository consultationsRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public ConsultationsPhoneService(ConsultationsRepository consultationsRepository, PatientRepository patientRepository,
                                     DoctorRepository doctorRepository) {
        this.consultationsRepository = consultationsRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public ConsultationsPhoneDto createConsultation(CreateConsultationsRequest createRequest) {
        doctorRepository.findById(createRequest.doctorId())
                .orElseThrow(() -> new MedBookingException("Doctor with ID " + createRequest.doctorId() + " does not exist. "
                                + LocalDateTime.now()));
        if (createRequest.date().isBefore(LocalDate.now()) == true){
            throw new MedBookingException("Consultation date must be in the future."
                    + LocalDateTime.now());
        }

        if(!consultationsRepository.findByDateAndTimeAndDoctorId(createRequest.date(),
                createRequest.time(),createRequest.doctorId()).isEmpty() ){
            throw new MedBookingException("Such a visit already exists. Enter a different date or time."
                    + LocalDateTime.now());
        }
        return mapperConsultationsEntityToDto(consultationsRepository.save(
                ConsultationsPhone.builder()
                .status(Status.AVAILABLE)
                .specialization(createRequest.specialization())
                .date(createRequest.date())
                .time(createRequest.time())
                .price(createRequest.price())
                .recommendations(createRequest.recommendations())
                .doctorId(createRequest.doctorId())
                .build()
        ));
    }

    public List<ConsultationsPhoneDto> getAllConsultation() {
        return consultationsRepository.findAll().stream()
                .map(o -> {
                    ConsultationsPhoneDto consultationsDto = mapperConsultationsEntityToDto(o);
                    return consultationsDto;
                }).toList();
    }

    public ConsultationsPhoneDto getConsultationById(Integer id) {
        return mapperConsultationsEntityToDto(consultationsRepository.findById(id)
                .orElseThrow(()->new MedBookingException("Consultation with ID " + id + " does not exist.", LocalDateTime.now())));
    }

    public ConsultationsPhoneDto consultationBooking(Integer patientId, Integer consultationId){
        patientRepository.findById(patientId)
                .orElseThrow(()->new MedBookingException("Patient with ID " + patientId + " does not exist.", LocalDateTime.now()));
        ConsultationsPhoneDto consultationsDto = mapperConsultationsEntityToDto(consultationsRepository.findById(consultationId)
                .orElseThrow(
                        () -> new MedBookingException("Consultation with ID " + consultationId + " does not exist.", LocalDateTime.now())));
        consultationsDto.setPatientId(patientId);
        consultationsDto.setStatus(Status.NOAVAILABLE);
        consultationsRepository.save(mapperConsultationsDtoToEntity(consultationsDto));
        return consultationsDto;
    }
    public ConsultationsPhoneDto cancellationConsultations(Integer consultationId){
        ConsultationsPhoneDto consultationsDto = mapperConsultationsEntityToDto(consultationsRepository.findById(consultationId)
                .orElseThrow(
                        () -> new MedBookingException("Consultation with ID " + consultationId + " does not exist.", LocalDateTime.now())));
        consultationsDto.setPatientId(null);
        consultationsDto.setStatus(Status.AVAILABLE);
        consultationsRepository.save(mapperConsultationsDtoToEntity(consultationsDto));
        return consultationsDto;
    }

    public PatientVisitAndConsultationResponse deleteConsultationsById(Integer id) {
        ConsultationsPhone consultations = consultationsRepository.findById(id)
                .orElseThrow(() -> new MedBookingException("Consultations with ID " + id + " does not exist.", LocalDateTime.now()));
        if(consultations.getPatientId() != null) {
            consultationsRepository.deleteById(id);
            PatientVisitAndConsultationResponse patient = mapperPatientToVisitCanceled(patientRepository.findById(consultations.getPatientId()).orElseThrow());
            patient.setDateVisit(consultations.getDate());
            patient.setTime(consultations.getTime());
            patient.setSpecialization(consultations.getSpecialization());
            return patient;
        }else {
            consultationsRepository.deleteById(id);
            return null;
        }
    }

    public List<PatientVisitAndConsultationResponse> deleteConsultationsByDoctorIdAndDay(Integer doctorId, LocalDate localDate){
        return consultationsRepository.findByDateAndDoctorId(localDate, doctorId).stream().map(o -> {
            if (o.getPatientId() != null) {
                PatientVisitAndConsultationResponse patient = mapperPatientToVisitCanceled(patientRepository.findById(o.getPatientId()).orElseThrow());
                patient.setDateVisit(o.getDate());
                patient.setSpecialization(o.getSpecialization());
                patient.setTime(o.getTime());
                consultationsRepository.deleteById(o.getId());
                return patient;
            }else {
                consultationsRepository.deleteById(o.getId());
                return null;
            }
        }).toList();
    }
    public List<PatientVisitAndConsultationResponse> findAllAppointmentsPatients(Integer doctorId, LocalDate localDate){
        return consultationsRepository.findAllAppointments(localDate, doctorId).stream().map(o -> {
                PatientVisitAndConsultationResponse patient = mapperPatientToVisitCanceled(patientRepository.findById(o.getPatientId()).orElseThrow());
                patient.setDateVisit(o.getDate());
                patient.setSpecialization(o.getSpecialization());
                patient.setTime(o.getTime());
                return patient;
        }).toList();
    }
}
