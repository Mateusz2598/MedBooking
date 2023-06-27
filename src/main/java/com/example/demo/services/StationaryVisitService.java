package com.example.demo.services;

import com.example.demo.dtos.StationaryVisitDto;
import com.example.demo.entities.Facility;
import com.example.demo.entities.StationaryVisit;
import com.example.demo.enums.Status;
import com.example.demo.exception.MedBookingException;
import com.example.demo.mappers.Mappers;
import com.example.demo.repositories.DoctorRepository;
import com.example.demo.repositories.FacilityRepository;
import com.example.demo.repositories.PatientRepository;
import com.example.demo.repositories.StationaryVisitRepository;
import com.example.demo.requests.CreateVisitRequest;
import com.example.demo.response.PatientVisitAndConsultationResponse;
import com.example.demo.response.StationaryVisitResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationaryVisitService implements Mappers {

    private final StationaryVisitRepository stationaryVisitRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final FacilityRepository facilityRepository;

    public StationaryVisitService(StationaryVisitRepository stationaryVisitRepository, PatientRepository patientRepository,
                                  DoctorRepository doctorRepository, FacilityRepository facilityRepository) {
        this.stationaryVisitRepository = stationaryVisitRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.facilityRepository = facilityRepository;
    }

    public StationaryVisitResponse createVisit(CreateVisitRequest createRequest) {
        doctorRepository.findById(createRequest.doctorId())
                .orElseThrow(() -> new MedBookingException("Doctor with ID " + createRequest.doctorId() + " does not exist. "
                                + LocalDateTime.now()));
        facilityRepository.findById(createRequest.facilityId())
                .orElseThrow(
                        () -> new MedBookingException("Facility with ID " + createRequest.facilityId() + " does not exist. "
                                + LocalDateTime.now()));

        if (createRequest.date().isBefore(LocalDate.now()) == true){
            throw new MedBookingException("Consultation date must be in the future."
                    + LocalDateTime.now());
        }
        return mapperVisitResponse(stationaryVisitRepository.save(
                StationaryVisit.builder()
                        .status(Status.AVAILABLE)
                        .specialization(createRequest.specialization())
                        .date(createRequest.date())
                        .time(createRequest.time())
                        .price(createRequest.price())
                        .recommendations(createRequest.recommendations())
                        .doctorId(createRequest.doctorId())
                        .facility(Facility.builder().id(createRequest.facilityId()).build())
                        .build()
        ));
    }

    public StationaryVisitResponse getVisitById(Integer id) {
        return mapperVisitResponse(stationaryVisitRepository.findById(id)
                .orElseThrow(() -> new MedBookingException("Visit with ID " + id + " does not exist.", LocalDateTime.now())));
    }

    public List<StationaryVisitResponse> getAllVisit() {
        return stationaryVisitRepository.findAll().stream().map(o -> mapperVisitResponse(o)).collect(Collectors.toList());
    }

    public StationaryVisitResponse visitBooking(Integer patientId, Integer visitId) {
        patientRepository.findById(patientId)
                .orElseThrow(() -> new MedBookingException("Patient with ID " + patientId + " does not exist.", LocalDateTime.now()));
        StationaryVisitDto visitDto = mapperVisitEntityToDto(stationaryVisitRepository.findById(visitId)
                .orElseThrow(
                        () -> new MedBookingException("Visit with ID " + visitId + " does not exist.", LocalDateTime.now())));
        visitDto.setPatientId(patientId);
        visitDto.setStatus(Status.NOAVAILABLE);
        stationaryVisitRepository.save(mapperVisitDtoToEntity(visitDto));
        return mapperVisitResponse(mapperVisitDtoToEntity(visitDto));
    }

    public StationaryVisitResponse cancellationVisit(Integer visitId) {
        StationaryVisitDto visitDto = mapperVisitEntityToDto(stationaryVisitRepository.findById(visitId)
                .orElseThrow(
                        () -> new MedBookingException("Visit with ID " + visitId + " does not exist.", LocalDateTime.now())));
        visitDto.setPatientId(null);
        visitDto.setStatus(Status.AVAILABLE);
        stationaryVisitRepository.save(mapperVisitDtoToEntity(visitDto));
        return mapperVisitResponse(mapperVisitDtoToEntity(visitDto));
    }

    public PatientVisitAndConsultationResponse deletedVisitById(Integer id) {
        StationaryVisit visit = stationaryVisitRepository.findById(id)
                .orElseThrow(() -> new MedBookingException("Visit with ID " + id + " does not exist.", LocalDateTime.now()));
        if (visit.getPatientId() != null) {
            stationaryVisitRepository.deleteById(id);
            PatientVisitAndConsultationResponse patient = mapperPatientToVisitCanceled(patientRepository.findById(visit.getPatientId()).orElseThrow());
            patient.setDateVisit(visit.getDate());
            patient.setTime(visit.getTime());
            patient.setSpecialization(visit.getSpecialization());
            return patient;
        } else {
            stationaryVisitRepository.deleteById(id);
            return null;
        }
    }

    public List<PatientVisitAndConsultationResponse> deleteVisitByDoctorIdAndDay(Integer doctorId, LocalDate localDate) {
        return stationaryVisitRepository.findVisitByDateAndDoctorId(localDate, doctorId).stream().map(o -> {
            if (o.getPatientId() != null) {
                PatientVisitAndConsultationResponse patient = mapperPatientToVisitCanceled(patientRepository.findById(o.getPatientId()).orElseThrow());
                patient.setDateVisit(o.getDate());
                patient.setSpecialization(o.getSpecialization());
                patient.setTime(o.getTime());
                stationaryVisitRepository.deleteById(o.getId());
                return patient;
            } else {
                stationaryVisitRepository.deleteById(o.getId());
                return null;
            }
        }).toList();
    }

    public List<PatientVisitAndConsultationResponse> findAllAppointmentsPatientToVisit(Integer doctorId, LocalDate localDate) {
        return stationaryVisitRepository.findAllVisitAppointments(localDate, doctorId).stream().map(o -> {
            PatientVisitAndConsultationResponse patient = mapperPatientToVisitCanceled(patientRepository.findById(o.getPatientId()).orElseThrow());
            patient.setDateVisit(o.getDate());
            patient.setSpecialization(o.getSpecialization());
            patient.setTime(o.getTime());
            return patient;
        }).toList();
    }

}
