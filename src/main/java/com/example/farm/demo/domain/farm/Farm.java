package com.example.farm.demo.domain.farm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Document(collection = "farms")
public class Farm {
    @Id private String id;

    @NonNull
    @Setter
    @NotBlank(message = "농장 이름을 입력해주세요")
    @Size(max=50, message = "농장 이름은 최대 50자 까지입니다.")
    private String farmName;

    @NonNull
    @Setter
    @NotBlank(message = "농장 카테고리 이름을 입력해주세요")
    @Size(max=30, message = "농장 카테고리 이름은 최대 30자 까지입니다.")
    private String farmCategory;

    @Setter
    private String UserId;

    private final Date createdAt;

    @Setter
    private Date updatedAt;

    Farm() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Farm(@NotNull String farmName, @NotNull String farmCategory){
        this.farmName = farmName;
        this.farmCategory = farmCategory;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

}
