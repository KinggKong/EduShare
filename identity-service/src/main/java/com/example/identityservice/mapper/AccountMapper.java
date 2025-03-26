package com.example.identityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.identityservice.dto.request.AuthenticationRequest;
import com.example.identityservice.dto.response.AuthenticationResponse;
import com.example.identityservice.entity.AccountEntity;
import com.example.identityservice.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface AccountMapper {
	@Mapping(target = "username", source = "username")
	@Mapping(target = "password", source = "password")
	AccountEntity toAccountEntity(AuthenticationRequest.AccountRegisterRequestDTO requestDTO);

	UserEntity toUserEntity(AuthenticationRequest.AccountRegisterRequestDTO requestDTO);

	AuthenticationResponse.LoginResponse toLoginResponse(UserEntity userEntity);

	@Mapping(target = "roleName", source = "roleEntity.roleName")
	AuthenticationResponse.RegisterResponse toRegisterResponse(AccountEntity accountEntity);
}
