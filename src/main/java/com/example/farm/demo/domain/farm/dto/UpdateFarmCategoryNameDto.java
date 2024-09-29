package com.example.farm.demo.domain.farm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFarmCategoryNameDto {
    @Schema(description = "새로운 카테고리 이름", example = "새로운 카테고리 이름")
    @NotBlank
    private String newCategoryName;
}
