package com.example.profileservice.service;

import com.example.profileservice.dto.response.UserResponseDTO;

public interface IUserService {
    UserResponseDTO.ProfileResponseDTO getUserById(Long id);
}
