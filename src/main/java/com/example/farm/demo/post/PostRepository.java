package com.example.farm.demo.post;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

//Repository MongoDB와 상호작용하기위한 레포지토리
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByAuthorId(String authorId);
    List<Post> findByTitleContaining(String title);
}