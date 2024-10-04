package com.example.farm.demo.domain.post.Dto;

import com.example.farm.demo.domain.post.model.ViewFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewModeDto {

    @Schema(description = "필터 (VIEW_ON_SALE, VIEW_NOT_ON_SALE, VIEW_ALL)", example = "VIEW_ON_SALE")
    ViewFilter viewFilter;

}
