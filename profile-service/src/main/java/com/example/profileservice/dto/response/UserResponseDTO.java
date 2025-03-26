package com.example.profileservice.dto.response;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

public class UserResponseDTO {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Accessors(chain = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ProfileResponseDTO {
        Long userEntityId;
        String firstName;
        String lastName;
        String email;
        String fullName;
        String phoneNumber;
        String avtUrl;
        Boolean gender;
    }
}
