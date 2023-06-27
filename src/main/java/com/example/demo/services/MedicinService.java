package com.example.demo.services;

import com.example.demo.dtos.MedicinDto;
import com.example.demo.exception.MedBookingException;
import com.example.demo.mappers.Mappers;
import com.example.demo.repositories.MedicinRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MedicinService implements Mappers {

    private final MedicinRepository medicinRepository;

    public MedicinService(MedicinRepository medicinRepository) {
        this.medicinRepository = medicinRepository;
    }

    public MedicinDto createMedicin (MedicinDto medicinDto){
        return mapperMedicinEntityToDto(medicinRepository.save(mapperMedicinDtoToEntity(medicinDto)));
    }

    public MedicinDto getMedicinById (Integer id){
        return mapperMedicinEntityToDto(medicinRepository.findById(id)
                .orElseThrow(()->new MedBookingException("Medicin with ID " + id + " does not exist. " + LocalDateTime.now())));
    }

    public List<MedicinDto> getAllMedicin (){
        return medicinRepository.findAll().stream()
                .map(o->{MedicinDto medicinDto = mapperMedicinEntityToDto(o);
                    return medicinDto;
                }).toList();
    }

    public void deleteMedicinById (Integer id){
        medicinRepository.findById(id)
                .orElseThrow(()->new MedBookingException("Medicin with ID " + id + " does not exist. " + LocalDateTime.now()));
        medicinRepository.deleteById(id);
    }
}
