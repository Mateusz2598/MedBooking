package com.example.demo.services;

import com.example.demo.dtos.AddressDto;
import com.example.demo.exception.MedBookingException;
import com.example.demo.mappers.Mappers;
import com.example.demo.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AddressService implements Mappers {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressDto getAddressById(Integer id){
        return mapperAddressEntityToDto(addressRepository.findById(id)
                .orElseThrow(()-> new MedBookingException("Address with ID " + id + " does not exist.", LocalDateTime.now())));
    }
    public List<AddressDto> getAddressAll(){
        return addressRepository.findAll().stream()
                .map(o -> { AddressDto addressDto = mapperAddressEntityToDto(o);
                    return addressDto;
                }).toList();
    }
}
