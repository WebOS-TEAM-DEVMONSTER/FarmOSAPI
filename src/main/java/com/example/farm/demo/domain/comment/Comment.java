package com.example.farm.demo.domain.comment;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "comments")
public class Comment {
    @Id private String id;

    @NonNull
    private String postId;

    @NonNull
    private String commentAuthorId;

    @NonNull
    private String commentAuthorName;


    private String commentId;

    @NotBlank
    private String content;

    private Date createdAt;
    private Date updatedAt;

    Comment() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
