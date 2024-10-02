package com.example.farm.demo.domain.auth.model;

import com.example.farm.demo.domain.auth.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;


@Data
@Builder
@Getter
@Setter
@Document(collection = "users")
public class User implements UserDetails {

    @Id
    private String id; // mongoDB에서 객체를 식별하기위함

    @NonNull
    @Size(max=20)
    @Indexed(unique = true)
    @NotBlank(message = "이름을 입력해주세요")
    private String username;

    @NonNull
    @NotBlank(message = "이메일을 입력해주세요")
    @Size(max = 50)
    @Email
    private String email;

    @NonNull
    @NotBlank(message = "비밀번호를 입력해주세요")
    @Size(max = 120)
    @JsonIgnore
    private String password;

    @NonNull
    @NotBlank(message = "전화번호를 입력해주세요")
    @Pattern(regexp = "^(010-\\d{4}-\\d{4}|010\\d{8})$", message = "Invalid phone number format")
    private String phoneNumber;

    private String profileImage;
    private Date createdAt;
    private Date updatedAt;


    private ERole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserDto convertToUserDto(){
        String tmpProfile = "";
        if(profileImage==null || !profileImage.isEmpty()){
            tmpProfile = profileImage;
        }

        return UserDto.builder()
                .id(id)
                .username(username)
                .profileImage(tmpProfile)
                .build();
    }
}



//import java.util.HashSet;
//import java.util.Set;
//
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Pattern;
//import jakarta.validation.constraints.Size;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.DBRef;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Date;

//@Document(collection = "users")
//public class User implements UserDetails{
//    @Id
//    private Long id; // mongoDB에서 객체를 식별하기위함
//
//    @NotBlank
//    @Size(max=20)
//    private String name;
//
//    @NotBlank
//    @Size(max = 50)
//    @Email
//    private String email;
//
//    @NotBlank
//    @Size(max = 120)
//    private String password;
//
//    @NotBlank
//    @Pattern(regexp = "^(010-\\d{4}-\\d{4}|010\\d{8})$", message = "Invalid phone number format")
//    private String phoneNumber;
//
//    private String profileImage;
//    private Date createdAt;
//    private Date updatedAt;
//
//
//    private ERole role;
//
//    public User(){
//
//    }
//
//    public User(String name, String email, String password, String phoneNumber) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.phoneNumber = phoneNumber;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public @NotBlank @Size(max = 20) String getName() {
//        return name;
//    }
//
//    public void setname(@NotBlank @Size(max = 20) String name) {
//        this.name = name;
//    }
//
//    public @NotBlank @Size(max = 50) @Email String getEmail() {
//        return email;
//    }
//
//    public void setEmail(@NotBlank @Size(max = 50) @Email String email) {
//        this.email = email;
//    }
//
//    public @NotBlank @Size(max = 120) String getPassword() {
//        return password;
//    }
//
//    public void setPassword(@NotBlank @Size(max = 120) String password) {
//        this.password = password;
//    }
//
//    public @NotBlank @Pattern(regexp = "^(010-\\d{4}-\\d{4}|010\\d{8})$", message = "Invalid phone number format") String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(@NotBlank @Pattern(regexp = "^(010-\\d{4}-\\d{4}|010\\d{8})$", message = "Invalid phone number format") String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getProfileImage() {
//        return profileImage;
//    }
//
//    public void setProfileImage(String profileImage) {
//        this.profileImage = profileImage;
//    }
//
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Date getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public ERole getRole() {
//        return role;
//    }
//
//    public void setRole(ERole role) {
//        this.role = role;
//    }
//}
