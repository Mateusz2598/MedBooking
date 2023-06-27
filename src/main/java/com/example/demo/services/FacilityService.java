package com.example.demo.services;

import com.example.demo.dtos.FacilityDto;
import com.example.demo.entities.Facility;
import com.example.demo.enums.Specialization;
import com.example.demo.exception.MedBookingException;
import com.example.demo.mappers.Mappers;
import com.example.demo.repositories.FacilityRepository;
import com.example.demo.requests.CreateFacilityRequest;
import com.example.demo.response.FacilityResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FacilityService implements Mappers {

    private final FacilityRepository facilityRepository;

    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public FacilityResponse createFacility (CreateFacilityRequest createFacilityRequest){
        FacilityDto facilityDto = createFacilityRequest.facilityDto();
        facilityDto.setAddress(mapperAddressDtoToEntity(createFacilityRequest.addressDto()));
        Facility facility = facilityRepository.save(mapperFacilityDtoToEntity(facilityDto));
        return mapperFacilityResponse(facility);
    }

    public FacilityResponse getFacilityById (Integer id){
        return mapperFacilityResponse(facilityRepository.findById(id)
                .orElseThrow(
                        () -> new MedBookingException("Facility with ID " + id + " does not exist. " + LocalDateTime.now())));
    }

    public List<FacilityResponse> getAllFacility (){
        return facilityRepository.findAll().stream()
                .map(o->{FacilityResponse facilityResponse = mapperFacilityResponse(o);
                    return facilityResponse;
                }).toList();
    }

    public Set<Specialization> specjalizationFacility (Integer facilityId){
        facilityRepository.findById(facilityId)
                .orElseThrow(() -> new MedBookingException("Facility with ID " + facilityId + " does not exist. " + LocalDateTime.now()));
        return facilityRepository.findById(facilityId)
                .map(o -> o.getDoctorSet()
                        .stream()
                        .map(a -> a.getSkills().getSpecialization())
                        .collect(Collectors.toSet())).orElseThrow(null);
    }

    public void deleteFacilityById (Integer id){
        facilityRepository.findById(id).orElseThrow(
                ()-> new MedBookingException("Facility with ID " + id + " does not exist.", LocalDateTime.now()));
        facilityRepository.deleteById(id);
    }
}
