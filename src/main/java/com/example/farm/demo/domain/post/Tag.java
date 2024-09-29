package com.example.farm.demo.domain.post;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "tags")
public class Tag {
    @Id
    private String id;

    @NotBlank
    @Size(max=30, message = "태그이름은 최대 30자까지 가능합니다")
    private String tagName;

    private List<String> postIDs;

}
