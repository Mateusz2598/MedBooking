package com.example.demo.services;

import com.example.demo.dtos.PrescriptionDto;
import com.example.demo.entities.Doctor;
import com.example.demo.entities.Patient;
import com.example.demo.entities.Prescription;
import com.example.demo.exception.MedBookingException;
import com.example.demo.exception.PatientException;
import com.example.demo.mappers.Mappers;
import com.example.demo.repositories.DoctorRepository;
import com.example.demo.repositories.MedicinRepository;
import com.example.demo.repositories.PatientRepository;
import com.example.demo.repositories.PrescriptionRepository;
import com.example.demo.requests.PrescriptionRequest;
import com.example.demo.response.PrescriptionResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrescriptionService implements Mappers {

    private final PrescriptionRepository prescriptionRepository;
    private final MedicinRepository medicinRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository, MedicinRepository medicinRepository,
                               PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.medicinRepository = medicinRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public PrescriptionDto createPrescription(PrescriptionDto prescriptionDto) {
        Optional.ofNullable(prescriptionDto.getMedicin())
                .ifPresent(medicinSet -> {
                    medicinSet.forEach(o -> {
                        medicinRepository.findById(o)
                                .orElseThrow(() -> new MedBookingException("Medicin with ID " + o + " does not exist."));
                    });
                });
        doctorRepository.findById(prescriptionDto.getDoctorId()).orElseThrow(
                        () -> new MedBookingException("Doctor with ID " + prescriptionDto.getDoctorId() + " does not exist. ",
                                LocalDateTime.now()));
        patientRepository.findById(prescriptionDto.getPatientId())
                .orElseThrow(() -> new PatientException("Patient with ID " + prescriptionDto.getPatientId() + " does not exist.",
                        LocalDateTime.now()));

        Prescription prescription = mapperPrescriptionDtoToEntity(prescriptionDto);
        if (prescription.getIssureDate() == null) {
            prescription.setIssureDate(LocalDate.now());
        }
        if (prescription.getExpirationDate() == null) {
            prescription.setExpirationDate(prescription.getIssureDate().plusDays(30));
        }
        if (prescription.getIssureDate().isBefore(LocalDate.now()) ||
                prescription.getExpirationDate().isBefore(prescription.getIssureDate())){
            throw new MedBookingException("Date of completion of prescription must not be earlier than date of issue of prescription.");
        }
        return mapperPrescriptionEntityToDto(prescriptionRepository.save(prescription));
    }

    public PrescriptionDto getPrescriptionById(Integer id) {
        return mapperPrescriptionEntityToDto(prescriptionRepository.findById(id)
                .orElseThrow(() -> new MedBookingException("Prescription with ID " + id + " does not exist.", LocalDateTime.now())));
    }

    public List<PrescriptionDto> getAllPrescription() {
        return prescriptionRepository.findAll().stream()
                .map(o -> {
                    return mapperPrescriptionEntityToDto(o);
                })
                .toList();
    }

    public void deletePrescription(Integer id) {
        prescriptionRepository.findById(id)
                .orElseThrow(() -> new MedBookingException("Prescription with ID " + id + " does not exist.", LocalDateTime.now()));
        prescriptionRepository.deleteById(id);
    }

    public PrescriptionResponse getPrescriptionPharmacy(PrescriptionRequest request) {
        Prescription prescription = prescriptionRepository.findByCodeAndPesel(request.code(), request.pesel())
                .orElseThrow(() -> new MedBookingException("Prescription does not exist.", LocalDateTime.now()));
        Patient patient = patientRepository.findById(prescription.getPatientId()).orElseThrow();
        Doctor doctor = doctorRepository.findById(prescription.getDoctorId()).orElseThrow();
        return PrescriptionResponse.builder()
                .namePatient(patient.getName() + " " + patient.getSurname())
                .nameDoctor(doctor.getName() + " " + doctor.getSurname())
                .code(prescription.getCode())
                .issureDate(prescription.getIssureDate())
                .medicin(prescription.getMedicin().stream().map(o -> mapperMedicinResponse(o)).collect(Collectors.toList()))
                .build();
    }
}
