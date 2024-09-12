package com.example.farm.demo.farm;

import org.springframework.data.annotation.Id;
import java.util.Date;
public class Farm {
    @Id private String id;

    private String farmName;
    private String farmCategory;
    private String UserId;
    private Date createdAt;

    Farm() {
        this.createdAt = new Date();
    }

    public String getId() {
        return id;
    }

    public String getFarmName() {
        return farmName;
    }
    public String getFarmCategory() {
        return farmCategory;
    }
    public String getUserId() {
        return UserId;
    }

    public String getCreatedAt() {
        return createdAt.toString();
    }


    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }
    public void setFarmCategory(String farmCategory) {
        this.farmCategory = farmCategory;
    }
    public void setUserId(String UserId) {
        this.UserId = UserId;
    }
}
