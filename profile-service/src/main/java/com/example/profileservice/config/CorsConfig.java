package com.example.profileservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Cho phép tất cả origin
        config.setAllowedOriginPatterns(Collections.singletonList("*"));

        // Cho phép tất cả phương thức HTTP (GET, POST, PUT, DELETE, ...)
        config.setAllowedMethods(Collections.singletonList("*"));

        // Cho phép tất cả header
        config.setAllowedHeaders(Collections.singletonList("*"));

        // Cho phép gửi thông tin xác thực (Authorization, Cookies, ...)
        config.setAllowCredentials(true);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
