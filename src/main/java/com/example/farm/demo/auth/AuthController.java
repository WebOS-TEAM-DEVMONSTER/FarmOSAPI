package com.example.farm.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "인증 API", description = "Auth API")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private CustomUserDetailService userDetailService;

    @PostMapping("/register")
    @Operation(summary = "회원가입", description = "회원가입을 진행합니다.")
    public ResponseEntity<String> register(@RequestBody MemberRegisterRequestDto registerRequest) {
        userDetailService.registerUser(registerRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인을 진행합니다.")
    public ResponseEntity<TokenInfo> login(@RequestBody MemberLoginRequestDto loginRequest) {
        TokenInfo tokenInfo = userDetailService.login(loginRequest);
        return ResponseEntity.ok(tokenInfo);
    }

    
}