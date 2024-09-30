package com.example.farm.demo.domain.farm;

import com.example.farm.demo.domain.auth.model.User;
import com.example.farm.demo.domain.auth.repository.UserRepository;
import com.example.farm.demo.domain.farm.dto.CreateFarmDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FarmService {
    
    @Autowired
    private FarmRepository farmRepository;
    private UserRepository userRepository;

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

    public void updateUserId (String farmId, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("인증 정보에 해당하는 유저 정보가 없습니다."));
        String id = user.getId();
        Farm farm = farmRepository.findById(farmId).orElseThrow(()->new RuntimeException("해당 ID의 농장이 없습니다."));
        farm.setUserId(id);
        farm.setUpdatedAt(new Date());
        farmRepository.save(farm);
    }

    public Farm createFarm(CreateFarmDto createFarmDto) {
        Farm farm = createFarmDto.convertDtoToFarm();
        return farmRepository.save(farm);
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
