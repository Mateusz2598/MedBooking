package com.example.demo.controllers;

import com.example.demo.dtos.AddressDto;
import com.example.demo.services.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    ResponseEntity<AddressDto> getAddressById(@RequestParam Integer id){
        return ResponseEntity.ok(addressService.getAddressById(id));
    }
    @GetMapping("all")
    ResponseEntity<List<AddressDto>> getAllAddress (){
        return ResponseEntity.ok(addressService.getAddressAll());
    }

}
