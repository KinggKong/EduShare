package com.example.profileservice.mapper;

import com.example.profileservice.dto.response.UserResponseDTO;
import com.example.profileservice.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "userEntityId", source = "id")
    UserResponseDTO.ProfileResponseDTO toProfileResponseDTO(UserEntity userEntity);
}
