package com.example.demo.services;

import com.example.demo.entities.Doctor;
import com.example.demo.entities.Facility;
import com.example.demo.entities.Skills;
import com.example.demo.exception.MedBookingException;
import com.example.demo.mappers.Mappers;
import com.example.demo.repositories.*;
import com.example.demo.requests.CreateDoctorRequest;
import com.example.demo.response.DoctorResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DoctorService implements Mappers {

    private final DoctorRepository doctorRepository;
    private final FacilityRepository facilityRepository;
    private final StationaryVisitRepository visitRepository;
    private final ConsultationsRepository consultationsRepository;
    private final SkillsRepository skillsRepository;

    public DoctorService(DoctorRepository doctorRepository, FacilityRepository facilityRepository,
                         StationaryVisitRepository visitRepository, ConsultationsRepository consultationsRepository,
                         SkillsRepository skillsRepository) {
        this.doctorRepository = doctorRepository;
        this.facilityRepository = facilityRepository;
        this.visitRepository = visitRepository;
        this.consultationsRepository = consultationsRepository;
        this.skillsRepository = skillsRepository;
    }

    public DoctorResponse createDoctor(CreateDoctorRequest doctorRequest) {
        doctorRequest.doctorDto().getFacility()
                .forEach(o -> facilityRepository.findById(o.getId())
                        .orElseThrow(() -> new MedBookingException("Facility with ID "
                                + o.getId() + " does not exist. " + LocalDateTime.now())));

        Skills skills = skillsRepository.findById(doctorRequest.doctorDto().getSkills().getId())
                .orElseThrow(() -> new MedBookingException("Skills with id " + doctorRequest.doctorDto().getSkills().getId() +
                        " does not exist.", LocalDateTime.now()));

        return mapperDoctorResponse(doctorRepository.save(
                Doctor.builder()
                        .name(doctorRequest.doctorDto().getName())
                        .surname(doctorRequest.doctorDto().getSurname())
                        .employeeNumber(doctorRequest.doctorDto().getEmployeeNumber())
                        .skills(skills)
                        .address(mapperAddressDtoToEntity(doctorRequest.addressDto()))
                        .facilitySet(doctorRequest.doctorDto().getFacility().stream().map(o-> mapperFacilityDtoToEntity(o)).collect(Collectors.toList()))
                        .build()));
    }

    public DoctorResponse getDoctorById(Integer id) {
        return mapperDoctorResponse(doctorRepository.findById(id)
                .orElseThrow(
                        () -> new MedBookingException("Doctor with ID " + id + " does not exist. ", LocalDateTime.now())));
    }

    public List<DoctorResponse> getAllDoctor() {
        return doctorRepository.findAll().stream()
                .map(o -> {
                    DoctorResponse doctorResponse = mapperDoctorResponse(o);
                    return doctorResponse;
                }).toList();
    }

    public void deletedDoctorById(Integer id) {
        doctorRepository.findById(id)
                .orElseThrow(
                        () -> new MedBookingException("Doctor with ID " + id + " does not exist. " + LocalDateTime.now()));
        doctorRepository.deleteById(id);
    }

    public DoctorResponse addFacilityToDoctor(Integer doctorId, Integer facilityId) {
        facilityRepository.findById(facilityId)
                .orElseThrow(
                        () -> new MedBookingException("Facility with ID " + facilityId + " does not exist. " + LocalDateTime.now()));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new MedBookingException("Doctor with ID " + doctorId + " does not exist. " + LocalDateTime.now()));
        Set<Integer> integerStream = doctor.getFacilitySet().stream().map(o -> o.getId()).collect(Collectors.toSet());
        integerStream.add(facilityId);
        doctor.setFacilitySet(integerStream.stream().map(o -> Facility.builder().id(o).build()).collect(Collectors.toList()));
        doctorRepository.save(doctor);
        return mapperDoctorResponse(doctor);
    }

    public DoctorResponse deleteFacilityToDoctor(Integer doctorId, Integer facilityId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new MedBookingException("Doctor with ID " + doctorId + " does not exist. " + LocalDateTime.now()));
        List<Integer> integerStream = doctor.getFacilitySet().stream().map(o -> o.getId()).collect(Collectors.toList());
        integerStream.remove(facilityId);
        doctor.setFacilitySet(integerStream.stream().map(o -> Facility.builder().id(o).build()).collect(Collectors.toList()));
        doctorRepository.save(doctor);
        return mapperDoctorResponse(doctor);
    }

    public Map<String, BigDecimal> salaryAllDoctorsFromVisits(LocalDate dateFrom, LocalDate dateTo) {
        Map<String, BigDecimal> mapSalaryVisit = new HashMap<>();
        doctorRepository.findAll().forEach(o -> {
            String nameAndSurname = o.getName() + " " + o.getSurname();
            BigDecimal price = visitRepository.sumSalaryDoctorVisit(dateFrom, dateTo, o.getId());
            if (mapSalaryVisit.containsKey(nameAndSurname) == true) {
                throw new MedBookingException("Key duplication. The method will not work correctly. " +
                        "Remove duplicate keys. Solution 1: The name of the doctor is the same. " +
                        "Change the doctor's name. " +
                        "Solution 2: Run the method on the data from the SQL file attached to the project." + LocalDateTime.now());
            }
            mapSalaryVisit.put(nameAndSurname, price == null ? BigDecimal.ZERO : price);
        });
        return mapSalaryVisit;
    }

    public Map<String, BigDecimal> salaryAllDoctorsFromConsultation(LocalDate datefrom, LocalDate dateTo) {
        Map<String, BigDecimal> mapSalaryConsultation = new HashMap<>();
        doctorRepository.findAll().forEach(o -> {
            String nameAndSurname = o.getName() + " " + o.getSurname();
            BigDecimal price = consultationsRepository.sumSalaryDoctorConsultation(datefrom, dateTo, o.getId());
            if (mapSalaryConsultation.containsKey(nameAndSurname) == true) {
                throw new MedBookingException("Key duplication. The method will not work correctly. " +
                        "Remove duplicate keys. Solution 1: The name of the doctor is the same. " +
                        "Change the doctor's name. " +
                        "Solution 2: Run the method on the data from the SQL file attached to the project." + LocalDateTime.now());
            }
            mapSalaryConsultation.put(nameAndSurname, price == null ? BigDecimal.ZERO : price);
        });
        return mapSalaryConsultation;
    }
}
