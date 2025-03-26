package com.example.identityservice.controller;

import com.example.identityservice.common.BaseResponse;
import com.example.identityservice.common.CommonResult;
import com.example.identityservice.dto.request.AuthenticationRequest;
import com.example.identityservice.service.IAuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "01. Auth")
@Slf4j
public class AuthenticationController {
    private final BaseResponse baseResponse;
    private final IAuthenticationService authenticationService;

    @Operation(summary = "Login with username and password")
    @PostMapping("/login")
    public CommonResult<?> login(@RequestBody @Valid AuthenticationRequest.AccountLoginRequestDTO requestDTO) {
        return baseResponse.getCommonResult(authenticationService.login(requestDTO));
    }

    @Operation(summary = "Register account to login in system")
    @PostMapping("/register")
    public CommonResult<?> register(@RequestBody @Valid AuthenticationRequest.AccountRegisterRequestDTO requestDTO) {
        return baseResponse.getCommonResult(authenticationService.register(requestDTO));
    }
}
