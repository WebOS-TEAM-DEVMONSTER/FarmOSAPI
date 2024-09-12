package com.example.farm.demo.post;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posts")
public class Post {
    @Id private String id;

    private String title;
    private String content;
    private String authorId;
    private String authorName;
    private String authorProfileImage;
    private Date createdAt;

    Post() {
        this.createdAt = new Date();
    }

    public String getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
   

    public String getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }
    public String getAuthorProfileImage() {
        return authorProfileImage;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }


    

}
