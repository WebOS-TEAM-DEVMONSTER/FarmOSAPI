package com.example.farm.demo.domain.post.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnValue {
    String value;

    public ReturnValue(String value){
        this.value = value;
    }
}
