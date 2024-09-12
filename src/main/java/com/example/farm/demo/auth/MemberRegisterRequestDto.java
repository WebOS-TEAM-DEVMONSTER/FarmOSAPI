package com.example.farm.demo.auth;

public class MemberRegisterRequestDto {
    
    private String email;
    private String password;
    private String name;
    private String phone;

    public MemberRegisterRequestDto(String memberId, String password, String name, String email, String phone) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }


}
