package com.example.farm.demo.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {
    String id;
    String username;
    String profileImage;

}
