package com.example.demo.response;

import com.example.demo.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacilityResponse {

    private Integer id;
    private String name;
    private Address address;
}
