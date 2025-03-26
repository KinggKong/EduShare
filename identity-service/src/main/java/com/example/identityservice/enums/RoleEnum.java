package com.example.identityservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    ROLE_ADMIN(1, "ROLE ADMIN"),
    ROLE_USER(2, "ROLE USER");

    private final int value;
    private final String description;
}
