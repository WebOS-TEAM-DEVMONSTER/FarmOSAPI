package com.example.farm.demo.domain.auth.repository;

import java.util.Optional;

import com.example.farm.demo.domain.auth.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

//MongoDB와 연동하기위한 저장소
public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByPhoneNumber(String phoneNumber);
}
