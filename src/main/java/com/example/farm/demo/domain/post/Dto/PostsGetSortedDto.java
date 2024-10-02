package com.example.farm.demo.domain.post.Dto;

import com.example.farm.demo.domain.post.model.Direction;
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
    @Schema(description = "페이징 처리(100개의 게시글 정보를 page 페이지, 페이지당 size 개의 post로 분할)", example = "CREATED_AT")
    private Integer page;

    @NonNull
    @Min(1)
    private Integer size;

    private SortOrder sortOrder;

    @Getter
    @Setter
    public static class SortOrder {
        @NonNull
        @Schema(description = "정렬할 키 현재 CREATED_AT 밖에 없음", example = "CREATED_AT")
        private String key;
        @NonNull
        @Schema(description = "정렬 방향 (ASC, DESC)", example = "ASC")
        private Direction direction;
    }

    public Pageable convertSortDtoToPageable(){
        Sort sort;
        if(sortOrder.direction == Direction.ASC){
            sort = Sort.by(Sort.Direction.ASC, sortOrder.key);
        } else {
            sort = Sort.by(Sort.Direction.DESC, sortOrder.key);
        }
        return PageRequest.of(page,size,sort);
    }

}
