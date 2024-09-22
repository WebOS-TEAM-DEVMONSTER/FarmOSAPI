package com.example.farm.demo.domain.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException, IOException {

        //JWT 토큰 추출 (Bearer빼주는거)
        String token = resolveToken((HttpServletRequest) request);

        // 토큰 유효성 검사
        if (token!=null && jwtTokenProvider.validateToken(token)) {
            //유효하면 authentication 정보 얻음
            //Authentication -> 사용자의 인증 정보
            //주요 구성요소: Principal, Credentials, Authorities, Authenticated 정보
            //Principal: 인증된 사용자 이름, 이메일, ID 등 포함
            //Credentials: 비밀번호나 토큰 같은 정보들
            //Authorities: 사용자의 권한 정보(ROLE_USER, ROLE_ADMIN) 같은것들
            //Details: 사용자의 추가적인 정보들(사용자의 IP주소나 세션 ID)
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    // 헤더에서 토큰 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}