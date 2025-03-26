package com.example.identityservice.service.impl;

import com.example.identityservice.config.JwtProvider;
import com.example.identityservice.dto.request.AuthenticationRequest;
import com.example.identityservice.dto.response.AuthenticationResponse;
import com.example.identityservice.entity.AccountEntity;
import com.example.identityservice.entity.UserEntity;
import com.example.identityservice.exception.AppException;
import com.example.identityservice.exception.ErrorCode;
import com.example.identityservice.mapper.AccountMapper;
import com.example.identityservice.repository.AccountRepository;
import com.example.identityservice.repository.UserRepository;
import com.example.identityservice.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService implements IAuthenticationService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public AuthenticationResponse.LoginResponse login(AuthenticationRequest.AccountLoginRequestDTO requestDTO) {
        AccountEntity existedAccount = accountRepository.findByUsername(requestDTO.getUsername()).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_USERNAME_NOT_FOUND));

        if (!passwordEncoder.matches(requestDTO.getPassword(), existedAccount.getPassword())) {
            throw new AppException(ErrorCode.LOGIN_FAILED);
        }
        return AuthenticationResponse.LoginResponse.builder()
                .accessToken(jwtProvider.generateTokenByUsername(existedAccount.getId(), existedAccount.getUsername(), existedAccount.getRoleEntity().getRoleName()))
                .accountId(existedAccount.getId())
                .username(existedAccount.getUsername())
                .refreshToken("refreshToken")
                .build();
    }

    @Override
    @Transactional
    public Long register(AuthenticationRequest.AccountRegisterRequestDTO requestDTO) {
        validateRegisterAccount(requestDTO);

        AccountEntity accountEntity = accountMapper.toAccountEntity(requestDTO)
                .setUserEntity(insertUserEntity(requestDTO))
                .withDefaultRole()
                .setPassword(encodePassword(requestDTO.getPassword()));

        return accountRepository.save(accountEntity).getId();

    }

    private UserEntity insertUserEntity(AuthenticationRequest.AccountRegisterRequestDTO requestDTO) {
        validateInsertUserEntity(requestDTO);

        UserEntity userEntity = accountMapper.toUserEntity(requestDTO)
                .setFullName(requestDTO.getFirstName() + " " + requestDTO.getLastName());

        return userRepository.saveAndFlush(userEntity);
    }

    private void validateRegisterAccount(AuthenticationRequest.AccountRegisterRequestDTO requestDTO) {
        if (accountRepository.findByUsername(requestDTO.getUsername()).isPresent()) {
            throw new AppException(ErrorCode.ACCOUNT_USERNAME_EXISTED);
        }
    }

    private void validateInsertUserEntity(AuthenticationRequest.AccountRegisterRequestDTO requestDTO) {
        if (userRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new AppException(ErrorCode.USER_EMAIL_EXISTED);
        }
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
