package com.example.farm.demo.domain.auth.dto;

import com.example.farm.demo.domain.auth.model.ERole;
import com.example.farm.demo.domain.auth.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
public class RegisterDto {

    @NotBlank(message = "이름을 입력해주세요.")
    private String username;

    @Email(message = "올바른 형식의 이메일 주소를 입력해주세요.")
    @NotBlank(message = "이메일 주소를 입력해주세요.")
    @Size(max = 50, message = "최대 50자 까지만 입력할 수 있어요")
    private String email;

    @NotBlank
    @Size(max = 120, message = "최대 120자 까지만 입력할 수 있어요")
    private String password;

    @NotBlank
    @Size(max = 120, message = "최대 120자 까지만 입력할 수 있어요")
    private String passwordCheck;


    @NotBlank
    @Pattern(regexp = "^(010-\\d{4}-\\d{4}|010\\d{8})$", message = "올바른 전화번호 형식을 입력해주세요.")
    private String phoneNumber;




    public User toEntity(String encPwd) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .phoneNumber(phoneNumber)
                .role(ERole.ROLE_USER)
                .build();
    }

}
