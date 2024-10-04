package com.example.farm.demo.domain.post;

import com.example.farm.demo.domain.post.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findByPostId(String postId, Sort sort);
}
