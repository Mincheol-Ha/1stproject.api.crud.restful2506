package com.example.mincheol1sr2.filters;

import com.example.mincheol1sr2.config.security.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = jwtTokenProvider.resolveToken(request);
        log.info("[JwtAuthFilter] jwtToken: {}", jwtToken);

        boolean valid = false;
        if (jwtToken != null) {
            valid = jwtTokenProvider.validateToken(jwtToken);
            log.info("[JwtAuthFilter] jwtToken 유효성 체크 결과: {}", valid);
        }

        if (jwtToken != null && valid) {
            Authentication auth = jwtTokenProvider.getAuthentication(jwtToken);
            log.info("[JwtAuthFilter] 인증 authorities : {}", auth.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        } else {
            log.info("[JwtAuthFilter] 토큰이 없거나 유효하지 않음");
        }

        filterChain.doFilter(request, response);
    }
}
