package com.example.identityservice.config;

import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@NonNullApi
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtTokenProvider;


    public JwtAuthenticationFilter(JwtProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = jwtTokenProvider.getJwtFromRequest(request);
        if (jwtToken != null && jwtTokenProvider.validateToken(jwtToken)) {
            SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.getAuthentication(jwtToken));
        } else {
            request.setAttribute("expired", "expired");
        }
        filterChain.doFilter(request, response);
    }
}
