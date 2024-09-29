package com.example.farm.demo.domain.farm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FarmService {
    
    @Autowired
    private FarmRepository farmRepository;

    public Farm getFarmById(String id) {
        return farmRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("해당 ID의 농장이 없습니다."));
    }



    public List<Farm> getFarmsByUserId(String userId) {
        return farmRepository.findByUserId(userId);
    }

    public List<Farm> getFarmsByFarmCategory(String farmCategory) {
        return farmRepository.findByFarmCategory(farmCategory);
    }

    public Farm createFarm(Farm farm) {
        return farmRepository.save(farm);
    }

    public void updateUserId(String farmId, String newUserId) {
        Farm farm = farmRepository.findById(farmId).orElseThrow(()->new RuntimeException("해당 ID의 농장이 없습니다."));
        farm.setUserId(newUserId);
        farmRepository.save(farm);
    }


    public void updateFarmName(String farmId, String newFarmName) {
        Farm farm = farmRepository.findById(farmId).orElseThrow(()->new RuntimeException("해당 ID의 농장이 없습니다."));
        farm.setFarmName(newFarmName);
        farmRepository.save(farm);
    }

    public void updateFarmCategoryName(String farmId, String newFarmCategoryName) {
        Farm farm = farmRepository.findById(farmId).orElseThrow(()->new RuntimeException("해당 ID의 농장이 없습니다."));
        farm.setFarmCategory(newFarmCategoryName);
        farmRepository.save(farm);
    }

    public void deleteFarm(String id) {
        farmRepository.deleteById(id);
    }



    

}
