package com.example.demo.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicinDto {

    private Integer id;
    @NotNull(message = "Name cannot be null.")
    @NotBlank
    private String name;
    @NotNull(message = "Dose cannot be null.")
    @NotBlank
    private String dose;
    @Max(value = 4, message = "Maximum number of packages is 4.")
    @Min(value = 1, message = "Minimum number of packages is 4.")
    private Integer quantity;
    private List<Integer> idPrescription;
}
