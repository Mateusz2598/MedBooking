package com.example.demo.services;

import com.example.demo.dtos.LaboratoryTestDto;
import com.example.demo.entities.Facility;
import com.example.demo.entities.LaboratoryTest;
import com.example.demo.enums.Status;
import com.example.demo.exception.MedBookingException;
import com.example.demo.mappers.Mappers;
import com.example.demo.repositories.*;
import com.example.demo.response.LaboratoryTestResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LaboratoryTestService implements Mappers {

    private final LaboratoryTestRepository laboratoryTestRepository;
    private final LabAssistantRepository labAssistantRepository;
    private final PatientRepository patientRepository;
    private final FacilityRepository facilityRepository;
    private final DoctorRepository doctorRepository;

    public LaboratoryTestService(LaboratoryTestRepository laboratoryTestRepository, LabAssistantRepository labAssistantRepository, PatientRepository patientRepository, FacilityRepository facilityRepository, DoctorRepository doctorRepository) {
        this.laboratoryTestRepository = laboratoryTestRepository;
        this.labAssistantRepository = labAssistantRepository;
        this.patientRepository = patientRepository;
        this.facilityRepository = facilityRepository;
        this.doctorRepository = doctorRepository;
    }

    public LaboratoryTestResponse addAssistentToTest(Integer idLaboratoryTest, Integer idAssistant) {
        labAssistantRepository.findById(idAssistant)
                .orElseThrow(() -> new MedBookingException("Lab assistant with ID " + idAssistant
                        + " does not exist.", LocalDateTime.now()));

        LaboratoryTest laboratoryTest = laboratoryTestRepository.findById(idLaboratoryTest)
                .orElseThrow(() -> new MedBookingException("Test with ID " + idLaboratoryTest + " does not exist.", LocalDateTime.now()));
        laboratoryTest.setLabAssistantId(idAssistant);
        laboratoryTest.setStatus(Status.AVAILABLE);

        return mapperLaboratoryTestResponse(laboratoryTestRepository.save(laboratoryTest));
    }

    public LaboratoryTestResponse createLaboratoryTest(LaboratoryTestDto laboratoryTestDto) {
        patientRepository.findById(laboratoryTestDto.getPatientId())
                .orElseThrow(() -> new MedBookingException("Patient with ID " + laboratoryTestDto.getPatientId() +
                " Does not exist. Add a patient ID that exists in the database. ", LocalDateTime.now()));

        Facility facility = facilityRepository.findById(laboratoryTestDto.getFacility().getId())
                .orElseThrow(() -> new MedBookingException("Facility with ID " + laboratoryTestDto.getFacility().getId() +
                        " Does not exist. Add a facility ID that exists in the database. ", LocalDateTime.now()));

        if (laboratoryTestDto.getDoctorId() != null) {
            doctorRepository.findById(laboratoryTestDto.getDoctorId())
                    .orElseThrow(() -> new MedBookingException("Doctor with ID " + laboratoryTestDto.getDoctorId() +
                    " Does not exist. Add a doctor ID that exists in the database. ", LocalDateTime.now()));
        }
        if (laboratoryTestDto.getDateTime().isBefore(LocalDateTime.now()) == true){
            throw new MedBookingException("LaboratoryTest dateTime must be in the future. "
                    + LocalDateTime.now());
        }
        LaboratoryTest laboratoryTest = mapperLaboratoryTestDtoToEntity(laboratoryTestDto);
        laboratoryTest.setStatus(Status.NOAVAILABLE);
        laboratoryTest.setFacility(facility);
        return mapperLaboratoryTestResponse(laboratoryTestRepository.save(laboratoryTest));
    }

    public LaboratoryTestResponse getTestById(Integer id) {
        return mapperLaboratoryTestResponse(laboratoryTestRepository.findById(id)
                .orElseThrow(
                        () ->new MedBookingException("LaboratoryTest with ID " + id + " does not exist.", LocalDateTime.now())));
    }

    public List<LaboratoryTestResponse> getAllTest() {
        return laboratoryTestRepository.findAll().stream()
                .map(o -> {
                    LaboratoryTestResponse laboratoryTestResponse = mapperLaboratoryTestResponse(o);
                    return laboratoryTestResponse;
                }).toList();
    }

    public void deleteLaboratoryTestById(Integer id) {
        laboratoryTestRepository.findById(id)
                .orElseThrow(()->new MedBookingException("Test with ID " + id + " does not exist.", LocalDateTime.now()));
        laboratoryTestRepository.deleteById(id);
    }
}