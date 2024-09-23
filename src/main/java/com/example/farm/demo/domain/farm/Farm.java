package com.example.farm.demo.domain.farm;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import java.util.Date;

@Getter
@Setter
public class Farm {
    @Id private String id;

    private String farmName;
    private String farmCategory;
    private String UserId;
    private Date createdAt;

    Farm() {
        this.createdAt = new Date();
    }

}
