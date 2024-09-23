package com.example.farm.demo.domain.farm_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;

@Service
public class FarmInfoService {
    
    @Autowired
    private FarmInfoRepository farmInfoRepository;

    public List<FarmInfo> getFarmInfosByFarmId(String farmId) {
        return farmInfoRepository.findByFarmId(farmId);
    }

    public FarmInfo createFarmInfo(String farmId, FarmInfo farmInfo) {
        farmInfo.setFarmId(farmId);
        return farmInfoRepository.save(farmInfo);
    }

    public List<FarmInfo> getTodayFarmInfos(String farmId) {
        Date today = new Date();
        return farmInfoRepository.findByFarmIdAndMeasureAt(farmId, today);
    }

}
