package com.example.farm.demo.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // 고객 ID로 고객 정보 조회
    public Optional<Member> getMemberById(String id) {
        return memberRepository.findById(id);
    }

    // 고객 정보 수정
    public Member updateMember(String id, Member customerDetails) {
        Optional<Member> customerOptional = memberRepository.findById(id);

        if (customerOptional.isPresent()) {
            Member customer = customerOptional.get();
            customer.setName(customerDetails.getName());
            customer.setPhoneNumber(customerDetails.getPhoneNumber());
            customer.setProfileImage(customerDetails.getProfileImage());
            customer.setUpdatedAt(new Date());
            return memberRepository.save(customer);
        } else {
            throw new RuntimeException("Customer not found with id " + id);
        }
    }

    //고객 정보 삭제
    public void deleteMember(String id) {
        memberRepository.deleteById(id);
    }
}