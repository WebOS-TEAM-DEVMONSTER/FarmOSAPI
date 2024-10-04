package com.example.farm.demo.domain.post.model;

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
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    private String postId;

    @NonNull
    private String commentAuthorId;

    @NonNull
    private String commentAuthorName;

    private String commentAuthorImage;

    private String parentCommentId;

    @NotBlank
    @Size(max=1000, message = "댓글은 최대 1000자까지 입력 가능합니다.")
    private String content;

    private List<Comment> taggedComments = new ArrayList<>();

    private Date createdAt = new Date();
    private Date updatedAt = new Date();

    public Comment(@NotNull String commentAuthorId, @NotNull String commentAuthorName, String commentAuthorImage,
                   String postId, String content, String parentCommentId) {
        this.commentAuthorId = commentAuthorId;
        this.commentAuthorName = commentAuthorName;
        this.commentAuthorImage = commentAuthorImage;
        this.postId = postId;
        this.content = content;
        this.parentCommentId = parentCommentId;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
    // 태그된 댓글 추가
    public void addTaggedComment(Comment taggedComment) {
        taggedComments.add(taggedComment);
    }

    // 태그된 댓글 제거
    public void removeTaggedComment(Comment taggedComment) {
        if (taggedComments.contains(taggedComment)) {
            taggedComments.remove(taggedComment);
        } else {
            throw new IllegalArgumentException("태그된 댓글을 찾을 수 없습니다.");
        }
    }

    // 태그된 댓글 정렬 후 반환
    public List<Comment> getTaggedComments() {
        taggedComments.sort(Comparator.comparing(Comment::getCreatedAt));
        return taggedComments;
    }
}
