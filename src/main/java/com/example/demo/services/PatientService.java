package com.example.demo.services;

import com.example.demo.dtos.PatientDto;
import com.example.demo.entities.Patient;
import com.example.demo.exception.PatientException;
import com.example.demo.mappers.Mappers;
import com.example.demo.repositories.PatientRepository;
import com.example.demo.requests.CreatePatientRequests;
import com.example.demo.requests.PeselRequest;
import com.example.demo.requests.UpdatePatientRequest;
import com.example.demo.response.PatientResponse;
import com.example.demo.response.PatientUpdateResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatientService implements Mappers {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {

        this.patientRepository = patientRepository;
    }
    public PatientUpdateResponse updateByPhoneMailAddress(UpdatePatientRequest updatePatientRequest, Integer id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientException("Patient with ID " + id + " does not exist.", LocalDateTime.now()));

        patient.setNumberPhone(updatePatientRequest.numberPhone());
        patient.setMail(updatePatientRequest.mail());
        patient.getAddress().setCity(updatePatientRequest.addressDto().getCity());
        patient.getAddress().setStreet(updatePatientRequest.addressDto().getStreet());
        patient.getAddress().setZipCode(updatePatientRequest.addressDto().getZipCode());
        patient.getAddress().setNumberStreet(updatePatientRequest.addressDto().getNumberStreet());
        patient.getAddress().setNumberFlat(updatePatientRequest.addressDto().getNumberFlat());
        return mapperPatientUpdateResponse(patientRepository.save(patient));
    }

    public PatientResponse createPatient(CreatePatientRequests createPatientRequests) {
        Patient patient = mapperPatientDtoToEntity(createPatientRequests.patientDto());
        patient.setAddress(mapperAddressDtoToEntity(createPatientRequests.addressDto()));
        return mapperPatientToResponse(patientRepository.save(patient));
    }

    public PatientDto getPatientByPesel(PeselRequest peselRequest) {
        return mapperPatientEntityToDto(patientRepository.findByPesel(peselRequest.pesel())
                .orElseThrow(() -> new PatientException("Patient with pesel " +
                        peselRequest.pesel() + " does not exist.", LocalDateTime.now())));
    }

    public PatientDto getPatientById(Integer id) {
        return mapperPatientEntityToDto(patientRepository.findById(id)
                .orElseThrow(() -> new PatientException("Patient with ID " + id + " does not exist.", LocalDateTime.now())));
    }

    public List<PatientResponse> getAllPatient() {
        return patientRepository.findAll().stream()
                .map(o -> {
                    PatientResponse patientResponse = mapperPatientToResponse(o);
                    return patientResponse;
                }).toList();
    }

    public void deletePatient(Integer id) {
        patientRepository.findById(id)
                .orElseThrow(() -> new PatientException("Patient with ID " + id + " does not exist.", LocalDateTime.now()));

        patientRepository.deleteById(id);
    }
}
