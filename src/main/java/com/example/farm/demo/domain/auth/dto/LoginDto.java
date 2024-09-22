package com.example.farm.demo.domain.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginDto {

    @Email(message = "올바른 형식의 이메일 주소를 입력해주세요.")
    @NotBlank(message = "이메일 주소를 입력해주세요.")
    @Size(max = 50, message = "최대 50자 까지만 입력할 수 있어요")
    private String email;

    @NotBlank
    @Size(max = 120, message = "최대 120자 까지만 입력할 수 있어요")
    private String password;

}
