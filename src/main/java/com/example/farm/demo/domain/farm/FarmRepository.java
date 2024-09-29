package com.example.farm.demo.domain.farm;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface FarmRepository extends MongoRepository<Farm, String> {
    List<Farm> findByUserId(String userId); // 유저이름으로 농장 검색
    List<Farm> findByFarmCategory(String farmCategory);



}