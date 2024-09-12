package com.example.farm.demo.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "유저 API", description = "Member API")
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/{id}")
    @Operation(summary = "회원 조회", description = "id를 통해 회원 정보를 조회합니다.")
    public Optional<Member> getMemberById(@PathVariable("id") String id) {
        if(id == null) {
            throw new RuntimeException("Customer not found with id " + id);
        }
        return memberService.getMemberById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "회원 수정", description = "id를 통해 회원 정보를 수정합니다.")
    public Member updateMember(@PathVariable("id") String id, @RequestBody Member memberDetails) {
        return memberService.updateMember(id, memberDetails);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "회원 삭제", description = "id를 통해 회원 정보를 삭제합니다.")
    public void deleteCustomer(@PathVariable("id") String id) {
        memberService.deleteMember(id);
    }
}