package com.example.farm.demo.domain.post.model;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.example.farm.demo.domain.auth.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Comment {
    @Id private String id;

    @NonNull
    private String commentAuthorId;

    @NonNull
    private String commentAuthorName;

    @NotBlank
    @Size(max=1000, message = "댓글은 최대 1000자까지 입력 가능합니다.")
    private String content;

    private List<Comment> taggedComments;

    private Date createdAt;
    private Date updatedAt;

    Comment() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Comment(User user, String content) {
        this.commentAuthorId = user.getId();
        this.commentAuthorName = user.getUsername();
        this.content = content;
        this.taggedComments = new ArrayList<>();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public void addTaggedComment(Comment taggedComment){
        taggedComments.add(taggedComment);
    }

    public void removeTaggedComment(Comment taggedComment) {
        // taggedComments 리스트에서 해당 댓글을 제거
        if (taggedComments.contains(taggedComment)) {
            taggedComments.remove(taggedComment);
        } else {
            throw new IllegalArgumentException("태그된 댓글을 찾을 수 없습니다.");
        }
    }

    public List<Comment> getTaggedComments() {
        taggedComments.sort(Comparator.comparing(Comment::getCreatedAt));
        return taggedComments;
    }
}
