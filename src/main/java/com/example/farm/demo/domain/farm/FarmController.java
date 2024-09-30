package com.example.farm.demo.domain.farm;

import com.example.farm.demo.domain.auth.service.UserService;
import com.example.farm.demo.domain.farm.dto.CreateFarmDto;
import com.example.farm.demo.domain.farm.dto.GetFarmCategoryNameDto;
import com.example.farm.demo.domain.farm.dto.UpdateFarmCategoryNameDto;
import com.example.farm.demo.domain.farm.dto.UpdateFarmNameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "농장 API", description = "Farm API")
@RestController
@RequestMapping("/api/v1/farms")
public class FarmController {

    @Autowired
    private FarmService farmService;

    @Autowired
    private UserService userService;

    @GetMapping("/my")
        @Operation(summary = "내 농장 조회", description = "자신이 소유한 농장을 조회합니다.")
        public ResponseEntity<List<Farm>> getMyFarms(Authentication authentication){
            String userName = authentication.getName();
            String userId = userService.findByUsername(userName).getId(); // 헤더 토큰정보를 통해 유저정보 추출 후 ID 추출
            System.out.println(userId);
            List<Farm> farms = farmService.getFarmsByUserId(userId); // 유저 정보가 ID인 농장 리스트 정보 추출

            return ResponseEntity.ok(farms); // 200 OK 응답
        }

    @GetMapping("/{farmId}")
    @Operation(summary = "농장 조회", description = "id를 통해 농장 정보를 조회합니다.")
    public ResponseEntity<Farm> getFarmById(@PathVariable("farmId") String id) {
        Farm farm = farmService.getFarmById(id);
        return ResponseEntity.ok(farm); // 200 OK 응답
    }

    @GetMapping("/category/")
    @Operation(summary = "카테고리 별 농장 조회", description = "카테고리 별 농장 정보를 조회합니다.")
    public ResponseEntity<List<Farm>> getFarmsByFarmCategory(@RequestParam GetFarmCategoryNameDto request) {
        String farmCategoryName = request.getNewCategoryName();
        List<Farm> farms = farmService.getFarmsByFarmCategory(farmCategoryName);
        return ResponseEntity.ok(farms); // 200 OK 응답
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "사용자 농장 조회", description = "userId를 통해 사용자의 농장 정보를 조회합니다.")
    public ResponseEntity<List<Farm>> getFarmsByUserId(@PathVariable("userId") String userId) {
        List<Farm> farms = farmService.getFarmsByUserId(userId);
        return ResponseEntity.ok(farms); // 200 OK 응답
    }

    @PatchMapping("/{farmId}/categoryName")
    @Operation(summary = "농장 카테고리 이름 변경", description = "농장의 카테고리 이름을 변경합니다.")
    public ResponseEntity<String> patchFarmCategoryName(@PathVariable("farmId") String farmId, @RequestBody UpdateFarmCategoryNameDto request){
        String newCategoryName = request.getNewCategoryName();
        farmService.updateFarmCategoryName(farmId, newCategoryName);
        return ResponseEntity.ok("농장 카테고리 이름을 변경하였습니다.");// 204 No Content 응답
    }

    @PatchMapping("/{farmId}/name")
    @Operation(summary = "농장 이름 변경", description = "농장의 이름을 변경합니다.")
    public ResponseEntity<String> patchFarmName(@PathVariable("farmId") String farmId, @RequestBody UpdateFarmNameDto request){
        String newFarmName = request.getNewFarmName();
        farmService.updateFarmName(farmId, newFarmName);
        return ResponseEntity.ok("농장 이름을 변경하였습니다.");
    }

    @PatchMapping("/{farmId}/user")
    @Operation(summary = "농장 주인 변경", description = "농장 주인을 현재 농장 주인으로 변경합니다.")
    public ResponseEntity<String> patchFarmUserId(@PathVariable("farmId") String farmId, Authentication authentication){
        farmService.updateUserId(farmId, authentication);
        return ResponseEntity.ok("농장 주인을 변경하였습니다.");
    }

    @PostMapping("/createFarm")
    @Operation(summary = "농장 생성", description = "농장을 생성합니다.")
    public ResponseEntity<Farm> createFarm(@RequestBody CreateFarmDto createFarmDto) {
        Farm createdFarm = farmService.createFarm(createFarmDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFarm); // 201 Created 응답
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "농장 삭제", description = "id를 통해 농장 정보를 삭제합니다. (시나리오 상 사용 x)")
    public ResponseEntity<Void> deleteFarm(@PathVariable("id") String id) {
        farmService.deleteFarm(id);
        return ResponseEntity.noContent().build(); // 204 No Content 응답
    }
}

