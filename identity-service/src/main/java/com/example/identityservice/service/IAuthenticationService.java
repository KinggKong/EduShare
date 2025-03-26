package com.example.identityservice.service;

import com.example.identityservice.dto.request.AuthenticationRequest;
import com.example.identityservice.dto.response.AuthenticationResponse;

public interface IAuthenticationService {
    AuthenticationResponse.LoginResponse login(AuthenticationRequest.AccountLoginRequestDTO requestDTO);

    Long register(AuthenticationRequest.AccountRegisterRequestDTO request);
}
