package com.example.farm.demo.domain.post;

import com.example.farm.demo.domain.post.model.Post;
import com.example.farm.demo.domain.post.model.SaleStatus;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

//Repository MongoDB와 상호작용하기위한 레포지토리
public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findBy(Pageable pageable);
    List<Post> findBySaleStatus(SaleStatus saleStatus, Pageable pageable);
    List<Post> findByTagsIn(List<String> tags, Pageable pageable);
    Page<Post> findByUser_Id(String userId, Pageable pageable);
    Page<Post> findByTitleContaining(String title, Pageable pageable);

    //List<Post> findByAuthorId(String authorId);

    List<Post> findByFarmId(String farmId);

    // 여러 태그로 필터링할 경우
}