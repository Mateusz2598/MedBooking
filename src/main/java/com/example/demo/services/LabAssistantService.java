package com.example.demo.services;

import com.example.demo.dtos.LabAssistantDto;
import com.example.demo.exception.MedBookingException;
import com.example.demo.mappers.Mappers;
import com.example.demo.repositories.FacilityRepository;
import com.example.demo.repositories.LabAssistantRepository;
import com.example.demo.response.LabAssistantResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LabAssistantService implements Mappers {

    private final LabAssistantRepository labAssistantRepository;
    private final FacilityRepository facilityRepository;

    public LabAssistantService(LabAssistantRepository labAssistantRepository, FacilityRepository facilityRepository) {
        this.labAssistantRepository = labAssistantRepository;
        this.facilityRepository = facilityRepository;
    }

    public LabAssistantResponse createLabAssistant(LabAssistantDto labAssistantDto) {
        facilityRepository.findById(labAssistantDto.getFacility().getId())
                .orElseThrow(() -> new MedBookingException("Facility with ID " + labAssistantDto
                        .getFacility().getId() + " does not exist.", LocalDateTime.now()));
        return mapperLabAssistantResponse(labAssistantRepository.save(mapperLabAssistantDtoToEntity(labAssistantDto)));
    }

    public LabAssistantResponse getLabAssistantById(Integer id) {
        return mapperLabAssistantResponse(labAssistantRepository.findById(id)
                .orElseThrow(
                        ()->new MedBookingException("LabAssistant with ID " + id + " does not exist.", LocalDateTime.now())));
    }

    public List<LabAssistantResponse> getAllLabAssistant() {
        return labAssistantRepository.findAll().stream()
                .map(o -> {
                    LabAssistantResponse assistantResponse = mapperLabAssistantResponse(o);
                    return assistantResponse;
                }).toList();
    }

    public void deleteLabAssistant(Integer id) {
        labAssistantRepository.findById(id)
                .orElseThrow(()->new MedBookingException("Assistant with ID " + id + " does not exist.", LocalDateTime.now()));
        labAssistantRepository.deleteById(id);
    }

}
