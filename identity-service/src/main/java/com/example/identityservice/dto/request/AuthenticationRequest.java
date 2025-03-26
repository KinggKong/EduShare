package com.example.identityservice.dto.request;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

public class AuthenticationRequest {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class AccountRegisterRequestDTO {
        @NotBlank(message = "FIRST_NAME_CANT_NULL_OR_EMPTY")
        @Length(message = "INVALID_LENGTH_FIRSTNAME", min = 2, max = 30)
        @Schema(name = "firstName", example = "Alex")
        String firstName;

        @NotBlank(message = "LAST_NAME_CANT_NULL_OR_EMPTY")
        @Length(message = "INVALID_LENGTH_LASTNAME", min = 2, max = 30)
        @Schema(name = "lastName", example = "Aloso")
        String lastName;

        @Length(message = "INVALID_LENGTH_EMAIL", min = 2)
        @NotBlank(message = "EMAIL_CANT_NULL_OR_EMPTY")
        @Schema(name = "email", example = "aloso@gmail.com")
        @Email(message = "INVALID_FORMAT_EMAIL")
        String email;

        @Length(message = "INVALID_LENGTH_USERNAME", min = 2, max = 20)
        @NotBlank(message = "USERNAME_CANT_NULL_OR_EMPTY")
        @Schema(name = "username", example = "aloso")
        String username;

        @Length(message = "INVALID_LENGTH_PASSWORD", min = 8, max = 20)
        @NotBlank(message = "PASSWORD_CANT_NULL_OR_EMPTY")
        @Schema(name = "password", example = "88888888")
        String password;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class AccountLoginRequestDTO {

        @Length(message = "INVALID_LENGTH_USERNAME", min = 2, max = 20)
        @NotBlank(message = "USERNAME_CANT_NULL_OR_EMPTY")
        @Schema(name = "username", example = "admin")
        String username;

        @Length(message = "INVALID_LENGTH_PASSWORD", min = 8, max = 20)
        @NotBlank(message = "PASSWORD_CANT_NULL_OR_EMPTY")
        @Schema(name = "password", example = "88888888")
        String password;
    }
}
