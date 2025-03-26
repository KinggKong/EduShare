package com.example.identityservice.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.identityservice.entity.AccountEntity;
public class SecurityUtils {
    public static Long getCurrentUserId() {
        return getCurrentAccount().getId();
    }

    public static String getCurrentUsername() {
        return getCurrentAccount().getUsername();
    }

    public static com.example.identityservice.entity.AccountEntity getCurrentAccount() {
        Object principal = getCurrentAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            return (AccountEntity) principal;
        }
        return null;
    }

    private static Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static boolean isAuthenticated() {
        return getCurrentAuthentication().isAuthenticated();
    }

    public static boolean hasRole(String role) {
        return getCurrentAuthentication().getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(role));
    }
}
