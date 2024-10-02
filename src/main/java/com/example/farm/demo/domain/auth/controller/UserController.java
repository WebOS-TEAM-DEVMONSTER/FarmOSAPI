package com.example.farm.demo.domain.auth.controller;

import com.example.farm.demo.domain.auth.dto.UserDto;
import com.example.farm.demo.domain.auth.model.User;
import com.example.farm.demo.domain.auth.repository.UserRepository;
import com.example.farm.demo.domain.auth.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "유저 API", description = "User API")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/my")
    @Operation(summary = "내 정보 조회", description = "내 정보를 조회합니다.")
    public ResponseEntity<UserDto> getUser(Authentication authentication) {
        // Authentication 객체에서 사용자명 추출
        String username = authentication.getName();
        System.out.println(username);
        // 사용자명을 이용하여 유저 정보 조회
        User user = userService.findByUsername(username);
        UserDto userDto = user.convertToUserDto();
        // 유저 정보 반환
        return ResponseEntity.ok(userDto);
    }


}