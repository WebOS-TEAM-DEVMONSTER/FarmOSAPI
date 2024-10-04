package com.example.farm.demo.domain.post.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class PostsGetSortedDto {

    @NonNull
    @Min(0)
    @Schema(description = "페이지 번호 (0부터 시작)", example = "0")
    private Integer page;

    @NonNull
    @Min(1)
    @Schema(description = "페이지당 게시글 수", example = "10")
    private Integer size;

    @NonNull
    @Schema(description = "정렬할 키 (현재는 CREATED_AT만 지원)", example = "CREATED_AT")
    private String key;

    @NonNull
    @Schema(description = "정렬 방향 (ASC, DESC)", example = "ASC")
    private Sort.Direction direction;

    public Pageable convertSortDtoToPageable() {
        //Sort sort = Sort.by(direction, key);
        Sort sort = Sort.by(Sort.Direction.ASC, "createdAt");
        return PageRequest.of(page, size, sort);
    }
}
