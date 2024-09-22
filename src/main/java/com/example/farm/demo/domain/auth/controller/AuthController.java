package com.example.farm.demo.domain.auth.controller;

import com.example.farm.demo.domain.auth.dto.LoginDto;
import com.example.farm.demo.domain.auth.dto.RegisterDto;
import com.example.farm.demo.domain.auth.service.UserService;
import com.example.farm.demo.domain.security.jwt.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    private UserService userService;

    @PostMapping("/isUser")
    @Operation(summary = "유저정보 확인", description = "해당 계정의 회원가입 여부를 확인합니다.")
    public ResponseEntity<String> isUser(String email){
        boolean result = userService.checkEmailExists(email);
        if(result){
            return ResponseEntity.ok("회원입니다");
        }
        return ResponseEntity.ok("회원이 아닙니다.");
    }

    @PostMapping("/register")
    @Operation(summary = "회원가입", description = "회원가입을 진행합니다.")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        userService.signup(registerDto);
        return ResponseEntity.ok("회원가입이 성공적으로 완료되었습니다.");
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인을 진행합니다.")
    public ResponseEntity<JwtToken> login(@RequestBody LoginDto loginDto) {
        JwtToken jwtToken = userService.login(loginDto);
        return ResponseEntity.ok(jwtToken);
    }

//    @PostMapping("/refresh")
//    @Operation(summary = "토큰 갱신", description = "토큰을 갱신합니다.")
//    public ResponseEntity<TokenInfo> refresh(@RequestBody TokenInfo tokenInfo) {
//        TokenInfo newTokenInfo = userDetailService.refresh(tokenInfo.getRefreshToken());
//        return ResponseEntity.ok(newTokenInfo);
//    }
//
//    @GetMapping("/search/{email}")
//    @Operation(summary = "이메일 찾기", description = "이메일을 찾습니다.")
//    public ResponseEntity<String> searchEmail(@RequestBody String email) {
//        String result = userDetailService.searchEmail(email);
//        return ResponseEntity.ok(result);
//    }
//
//    @PatchMapping("/setPassword")
//    @Operation(summary = "비밀번호 변경", description = "비밀번호를 변경합니다.")
//    public ResponseEntity<String> setPassword(@RequestBody MemberSetPasswordRequestDto setPasswordRequest) {
//        userDetailService.setPassword(setPasswordRequest);
//        return ResponseEntity.ok("비밀번호가 변경되었습니다.");
//    }
}