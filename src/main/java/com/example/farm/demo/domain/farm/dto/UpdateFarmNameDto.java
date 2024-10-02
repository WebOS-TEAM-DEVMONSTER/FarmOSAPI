package com.example.farm.demo.domain.farm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFarmNameDto {
    @Schema(description = "새로운 농장 이름", example = "새로운 농장 이름")
    @NotBlank
    private String newFarmName;
}


