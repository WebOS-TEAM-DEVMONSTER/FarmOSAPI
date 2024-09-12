package com.example.farm.demo.auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByMemberId(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    
private AuthenticationManager getAuthenticationManager() throws Exception {
    if (authenticationManager == null) {
        authenticationManager = authenticationConfiguration.getAuthenticationManager();
    }
    return authenticationManager;
}

public TokenInfo login(MemberLoginRequestDto memberLoginRequestDto) {
    try {
        Authentication authentication = getAuthenticationManager().authenticate(
            new UsernamePasswordAuthenticationToken(
                memberLoginRequestDto.getMemberId(),
                memberLoginRequestDto.getPassword()
            )
        );

        // 인증이 성공하면 JWT 토큰을 생성하여 반환
        return jwtTokenProvider.generateToken(authentication);
    } catch (AuthenticationException e) {
        throw new RuntimeException("로그인 실패: " + e.getMessage());
    } catch (Exception e) {
        throw new RuntimeException("AuthenticationManager 초기화 실패: " + e.getMessage());
    }
}
    // 회원가입 시 비밀번호를 암호화하여 저장하는 메서드
    public void registerUser(MemberRegisterRequestDto memberRegisterRequestDto) {
        // 비밀번호를 암호화
        memberRegisterRequestDto.setPassword(passwordEncoder.encode(memberRegisterRequestDto.getPassword()));
        
        Member member = new Member();
        
        member.setEmail(memberRegisterRequestDto.getEmail());
        member.setName(memberRegisterRequestDto.getName());
        member.setPhoneNumber(memberRegisterRequestDto.getPhone());
        
        member.setCreatedAt(new Date());
        member.setUpdatedAt(new Date());
        // 사용자 정보 저장
        memberRepository.save(member);
    }
}