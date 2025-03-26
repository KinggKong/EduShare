package com.example.profileservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "UNCATEGORIZED ERROR"),
    USER_NOT_FOUND(20000, "USER NOT FOUND"),
    INVALID_ARGUMENT(2000, "INVALID ARGUMENT"),
    ;

    private int code;
    private String message;
}
