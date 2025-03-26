package com.example.identityservice.common;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Component
public class BaseResponse {
    public <T> CommonResult<T> getCommonResult(T data) {
        return CommonResult.<T>builder()
                .code(1000)
                .message("success")
                .data(data)
                .build();
    }
}
