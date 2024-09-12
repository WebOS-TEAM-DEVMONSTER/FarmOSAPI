package com.example.farm.demo.auth;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "members")  // MongoDB에서 "members"라는 컬렉션과 매핑
public class Member implements UserDetails {

    @Id
    private String memberId;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String profileImage;
    private Date createdAt;
    private Date updatedAt;

    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
      }
    
      public void setName(String name) {
        this.name = name;
      }
    
      public String getPhoneNumber() {
        return phoneNumber;
      }
    
      public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
      }
    
      public String getProfileImage() {
        return profileImage;
      }
    
      public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
      }
    
      public Date getCreatedAt() {
        return createdAt;
      }
    
      public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
      }
      
      public Date getUpdatedAt() {
        return updatedAt;
      }
    
      public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
      }
}