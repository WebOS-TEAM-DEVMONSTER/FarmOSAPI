package com.example.farm.demo.comment;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class Comment {
    @Id private String id;
    private String postId;
    private String commentAuthorId;
    private String commentAuthorName;
    private String commentId;
    private String content;
    private Date createdAt;
    private Date updatedAt;

    Comment() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public String getId() {
        return id;
    }

    public String getPostId() {
        return postId;
    }

    public String getcommentAuthorId() {
        return commentAuthorId;
    }
    
    public String getCommentAuthorName() {
        return commentAuthorName;
    } 


    public String getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }


}
