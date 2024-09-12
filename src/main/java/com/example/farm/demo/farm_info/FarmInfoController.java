package com.example.farm.demo.farm_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "농장 측정 정보 API", description = "FarmInfo API")
@RestController
@RequestMapping("/api/v1/farms/{farmId}/farmInfos")
public class FarmInfoController {
    
    @Autowired
    private FarmInfoService farmInfoService;

    @GetMapping
    @Operation(summary = "농장 정보 조회", description = "농장 정보를 조회합니다.")
    public List<FarmInfo> getFarmInfosByFarmId(@PathVariable("farmId") String farmId) {
        return farmInfoService.getFarmInfosByFarmId(farmId);
    }

    @GetMapping("/today")
    @Operation(summary = "오늘 농장 정보 조회", description = "오늘 농장 정보를 조회합니다.")
    public List<FarmInfo> getTodayFarmInfos(@PathVariable("farmId") String farmId) {
        return farmInfoService.getTodayFarmInfos(farmId);
    }
    
    @PostMapping
    @Operation(summary = "농장 정보 생성", description = "농장 정보를 생성합니다.")
    public FarmInfo postMethodName(@PathVariable("farmId") String farmId, @RequestBody FarmInfo farmInfo) {
        return farmInfoService.createFarmInfo(farmId, farmInfo);
    }
    
}
