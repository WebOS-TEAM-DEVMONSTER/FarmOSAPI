package com.example.farm.demo.domain.farm.dto;

import com.example.farm.demo.domain.farm.Farm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
public class CreateFarmDto {

    @NotBlank
    @Size(max=50, message = "농장 이름은 최대 50자 까지입니다.")
    @Schema(description = "생설할 농장 이름", example = "생설할 농장 이름")
    private String farmName;

    @NotBlank(message = "농장 카테고리 이름을 입력해주세요")
    @Size(max=30, message = "농장 카테고리 이름은 최대 30자 까지입니다.")
    @Schema(description = "생설할 농장 카테고리 이름", example = "생설할 농장 카테고리 이름")
    private String farmCategory;

    public Farm convertDtoToFarm(){
        return new Farm(this.farmName, this.farmCategory);
    }

}
