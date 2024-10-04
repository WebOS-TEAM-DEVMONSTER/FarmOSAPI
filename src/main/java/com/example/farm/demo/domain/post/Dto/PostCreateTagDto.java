package com.example.farm.demo.domain.post.Dto;

import com.example.farm.demo.domain.auth.model.User;
import com.example.farm.demo.domain.farm.Farm;
import com.example.farm.demo.domain.post.model.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostCreateTagDto {
    @NonNull
    @NotBlank
    @Size(max = 50)
    @Schema(description = "게시글 제목", example = "게시글 제목")
    private String title;

    @Schema(description = "게시글 가격", example = "게시글 가격")
    private Long price; // null-true 처리 이유: 가격 정보를 공개하고싶지 않은 사용자도 있을 것

    @NonNull
    @NotBlank
    @Size(max = 5000)
    @Schema(description = "게시글 내용", example = "게시글 내용")
    private String content;

    @NonNull
    @NotBlank
    @Schema(description = "농장 id", example = "농장 id")
    private String farmId;

    @Size(max = 5, message = "태그는 최대 5개까지만 입력 가능합니다.")
    @Schema(description = "태그")
    private ArrayList<
             @Size(max = 30, message = "각 태그는 최대 30자까지 입력 가능합니다.")
             String
            > tags;

    @Schema(description = "농작물 이미지")
    private ArrayList<String> cropsImages;

    public Post getPostFromDto(User user, Farm farm){
        return new Post(title, price, content, tags, farm, user, cropsImages);
    }
}
