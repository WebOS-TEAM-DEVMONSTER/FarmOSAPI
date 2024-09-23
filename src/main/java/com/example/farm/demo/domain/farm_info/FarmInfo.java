package com.example.farm.demo.domain.farm_info;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    public String getId() {
        return id;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public Date getMeasureAt() {
        return measureAt;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getSoilMoisture() {
        return soilMoisture;
    }

    public void setSoilMoisture(double soilMoisture) {
        this.soilMoisture = soilMoisture;
    }

    public double getLightIntensity() {
        return lightIntensity;
    }

    public void setLightIntensity(double lightIntensity) {
        this.lightIntensity = lightIntensity;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    
}
