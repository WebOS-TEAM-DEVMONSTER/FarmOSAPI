package com.example.farm.demo.domain.auth.service;


import com.example.farm.demo.domain.auth.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.farm.demo.domain.auth.dto.LoginDto;
import com.example.farm.demo.domain.auth.dto.RegisterDto;
import com.example.farm.demo.domain.auth.model.User;
import com.example.farm.demo.domain.auth.repository.UserRepository;
import com.example.farm.demo.domain.security.jwt.JwtToken;
import com.example.farm.demo.domain.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {


    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserService(BCryptPasswordEncoder encoder, UserRepository repository, AuthenticationManagerBuilder authenticationManagerBuilder, JwtTokenProvider jwtTokenProvider) {
        this.encoder = encoder;
        this.userRepository = repository;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        System.out.println("실행됨");
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(email + "를 찾을 수 없습니다");
        }

        User foundUser = userOptional.get();

        // 권한을 설정하는 부분: ERole을 GrantedAuthority로 변환
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(foundUser.getRole().name()));

        // 권한 리스트를 UserDetails 객체에 포함시켜 반환
        return new org.springframework.security.core.userdetails.User(
                foundUser.getUsername(),
                foundUser.getPassword(),
                authorities // 여기에 권한 리스트 추가
        );
    }

    public JwtToken login(LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        // 검증
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        System.out.println("실행 22");


        // 검증된 인증 정보로 JWT 토큰 생성

        return jwtTokenProvider.generateToken(authentication);
    }

    public String signup(RegisterDto registerDto) {
        boolean checkEmail = checkEmailExists(registerDto.getEmail());
        boolean checkPassword = Objects.equals(registerDto.getPassword(), registerDto.getPasswordCheck());
        boolean checkPhoneNumber = checkPhoneNumber(registerDto.getPhoneNumber());

        if (checkEmail) {
            throw new IllegalArgumentException("이미 존재하는 유저입니다.");
        }

        if(!checkPassword){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if(checkPhoneNumber){
            throw new IllegalArgumentException("이미 존재하는 전화번호입니다.");
        }



        String encPwd = encoder.encode(registerDto.getPassword());

        User user = userRepository.save(registerDto.toEntity(encPwd));

        if(user==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return user.getId();
    }

    public boolean checkEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean checkPhoneNumber(String phoneNumber){
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("해당 유저 이름을 찾을 수 없습니다."));
    }

}


