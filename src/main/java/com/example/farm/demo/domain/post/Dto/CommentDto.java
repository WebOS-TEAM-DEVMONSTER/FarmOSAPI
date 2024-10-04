package com.example.farm.demo.domain.post.Dto;

import com.example.farm.demo.domain.auth.model.User;
import com.example.farm.demo.domain.post.model.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    @NotBlank
    @Size(max=1000, message = "댓글은 최대 1000자까지 입력 가능합니다.")
    private String content;

    public Comment convertDtoToComment(User user, String postId, String parentCommentId){
        return new Comment(user.getId(), user.getUsername(), user.getProfileImage(), postId, content, parentCommentId);
    }
}
