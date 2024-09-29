package com.example.farm.demo.domain.post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

public interface TagRepository extends MongoRepository<Tag, String>{

    // tagName을 사용하여 farmIDs 리스트에 농장 아이디 추가 (중복 방지)
    @Query("{ 'tagName': ?0 }")
    @Update("{ '$addToSet': { 'PostIDs': ?1 } }")
    void addFarmIdToTag(String tagName, String postId);

    // tagName을 사용하여 farmIDs 리스트에서 특정 농장 아이디 삭제
    @Query("{ 'tagName': ?0 }")
    @Update("{ '$pull': { 'PostIDs': ?1 } }")
    void removeFarmIdFromTag(String tagName, String postId);

    // 모든 Tag 컬렉션에서 특정 farmID(혹은 postId)를 삭제하는 메서드
    @Query("{ }")  // 빈 쿼리를 통해 모든 문서를 대상으로 함
    @Update("{ '$pull': { 'farmIDs': ?0 } }")
    void removePostIdFromAllTags(String postId);
}
