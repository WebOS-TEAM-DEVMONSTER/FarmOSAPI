package com.example.farm.demo.auth;

import lombok.Data;

@Data
public class MemberLoginRequestDto {
    private String email;
    private String password;

    public MemberLoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getMemberId() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
