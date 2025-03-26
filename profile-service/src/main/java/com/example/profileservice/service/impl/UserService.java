package com.example.profileservice.service.impl;

import com.example.profileservice.dto.response.UserResponseDTO;
import com.example.profileservice.entity.UserEntity;
import com.example.profileservice.exception.AppException;
import com.example.profileservice.exception.ErrorCode;
import com.example.profileservice.mapper.UserMapper;
import com.example.profileservice.repository.UserRepository;
import com.example.profileservice.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO.ProfileResponseDTO getUserById(Long id) {
        UserEntity userEntity = findById(id);
        return userMapper.toProfileResponseDTO(userEntity);
    }

    private UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }
}
