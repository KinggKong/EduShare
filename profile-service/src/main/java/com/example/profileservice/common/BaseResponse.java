package com.example.profileservice.common;

import lombok.Data;
import org.springframework.stereotype.Component;

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
