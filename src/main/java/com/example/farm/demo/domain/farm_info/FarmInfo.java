package com.example.farm.demo.domain.farm_info;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "farmInfos")
public class FarmInfo {
    @Id private String id;
    private String farmId;
    private Date measureAt;
    private double temperature;
    private double humidity;
    private double soilMoisture;
    private double lightIntensity;
    private double ph;

    FarmInfo() {
        this.measureAt = new Date();
    }


    
}
