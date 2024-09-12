package com.example.farm.demo.farm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmService {
    
    @Autowired
    private FarmRepository farmRepository;


    public Optional<Farm> getFarmById(String id) {
        return farmRepository.findById(id);
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

    public Farm updateFarm(String id, Farm farmDetails) {
        Optional<Farm> farmOptional = farmRepository.findById(id);

        if (farmOptional.isPresent()) {
            Farm farm = farmOptional.get();
            farm.setFarmName(farmDetails.getFarmName());
            farm.setFarmCategory(farmDetails.getFarmCategory());
            return farmRepository.save(farm);
        } else {
            throw new RuntimeException("헤딩 ID에 해당하는 농장이 없습니다. " + id);
        }
    }

    public void deleteFarm(String id) {
        farmRepository.deleteById(id);
    }



    

}
