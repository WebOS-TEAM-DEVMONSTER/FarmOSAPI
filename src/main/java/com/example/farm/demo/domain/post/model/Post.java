package com.example.farm.demo.domain.post.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.example.farm.demo.domain.auth.model.User;
import com.example.farm.demo.domain.farm.Farm;
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
    private Long price; // null-true 처리 이유: 가격 정보를 공개하고싶지 않은 사용자도 있을 것
    @NonNull
    @NotBlank
    private String content;
    private List<String> tags;
    private List<String> cropsImages;

    @NonNull
    private Farm farm;
    @NonNull
    private User author;
    @NonNull
    @NotBlank
    private Long score;
    @CreatedDate
    private Date createdAt;
    private Date updatedAt;
    // Embedded 모델링
    private List<Comment> comments;

    public Post(@NonNull String title, Long price, @NonNull String content, List<String> tags, @NonNull Farm farm, @NonNull User user, List<String> cropsImages) {
        this.title = title;
        this.price = price;
        this.content = content;
        this.tags = tags;
        this.cropsImages = cropsImages;
        this.farm = farm;
        this.author = user;


        this.score = 0L;
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.comments= new ArrayList<>();
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        // taggedComments 리스트에서 해당 댓글을 제거
        if (comments.contains(comment)) {
            comments.remove(comment);
        } else {
            throw new IllegalArgumentException("태그된 댓글을 찾을 수 없습니다.");
        }
    }

    public List<Comment> sortedComments() {
        comments.sort(Comparator.comparing(Comment::getCreatedAt));
        List<Comment> newComment = new ArrayList<>();
        for(Comment comment : comments){

        }
        return comments;
    }
}
