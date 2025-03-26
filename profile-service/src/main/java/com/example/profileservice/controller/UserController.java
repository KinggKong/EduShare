package com.example.profileservice.controller;

import com.example.profileservice.common.BaseResponse;
import com.example.profileservice.common.CommonResult;
import com.example.profileservice.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profiles")
@Tag(name = "01. Profile")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final BaseResponse baseResponse;

    @Operation(summary = "Get profile of UserEntity by userEntityId")
    @GetMapping("/{id}")
    public CommonResult<?> getProfileByUserEntityId(@PathVariable Long id) {
        return baseResponse.getCommonResult(userService.getUserById(id));
    }
}
