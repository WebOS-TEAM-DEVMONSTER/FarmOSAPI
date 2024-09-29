package com.example.farm.demo.domain.post;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "posts")
public class Post {
    @Id private String id;

    @NonNull
    @NotBlank
    @Size(max = 50)
    private String title;

    @NonNull
    @NotBlank
    private String content;

    private List<String> tags;

    @NonNull
    private String farmId;

    @NonNull
    private String authorId;

    private List<String> authorProfileImages;

    @CreatedDate
    private Date createdAt;

    // Embedded 모델링
    private List<Comment> comments;
}
