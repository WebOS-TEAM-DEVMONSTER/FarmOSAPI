package com.example.farm.demo.farm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;


@Tag(name = "농장 API", description = "Farm API")
@RestController
@RequestMapping("/api/v1/farms")
public class FarmController {
    
    @Autowired
    private FarmService farmService;

    @GetMapping("/{id}")
    @Operation(summary = "농장 조회", description = "id를 통해 농장 정보를 조회합니다.")
    public Optional<Farm> getFarmById(@PathVariable("id") String id) {
        return farmService.getFarmById(id);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "사용자 농장 조회", description = "userId를 통해 사용자의 농장 정보를 조회합니다.")
    public List<Farm> getFarmsByUserId(@PathVariable("userId") String userId) {
        return farmService.getFarmsByUserId(userId);
    }

    @GetMapping("/category/{farmCategory}")
    @Operation(summary = "농장 카테고리 조회", description = "farmCategory를 통해 농장 카테고리 정보를 조회합니다.")
    public List<Farm> getFarmsByFarmCategory(@PathVariable("farmCategory") String farmCategory) {
        return farmService.getFarmsByFarmCategory(farmCategory);
    }

    @PostMapping
    @Operation(summary = "농장 생성", description = "농장을 생성합니다.")
    public Farm createFarm(@RequestBody Farm farm) {
        return farmService.createFarm(farm);
    }

    @PutMapping("/{id}")
    @Operation(summary = "농장 수정", description = "id를 통해 농장 정보를 수정합니다.")
    public Farm updateFarm(@PathVariable("id") String id, @RequestBody Farm farmDetails) {
        return farmService.updateFarm(id, farmDetails);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "농장 삭제", description = "id를 통해 농장 정보를 삭제합니다.")
    public void deleteFarm(@PathVariable("id") String id) {
        farmService.deleteFarm(id);
    }
}
