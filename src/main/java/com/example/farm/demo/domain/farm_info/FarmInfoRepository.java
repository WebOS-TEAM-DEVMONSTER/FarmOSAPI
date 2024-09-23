package com.example.farm.demo.domain.farm_info;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Date;

public interface FarmInfoRepository extends MongoRepository<FarmInfo, String> {
    List<FarmInfo> findByFarmId(String farmId);
    List<FarmInfo> findByFarmIdAndMeasureAt(String farmId, Date measureAt);
}
