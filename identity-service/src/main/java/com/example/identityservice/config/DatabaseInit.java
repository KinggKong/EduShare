package com.example.identityservice.config;

import com.example.identityservice.entity.AccountEntity;
import com.example.identityservice.entity.RoleEntity;
import com.example.identityservice.entity.UserEntity;
import com.example.identityservice.enums.RoleEnum;
import com.example.identityservice.repository.AccountRepository;
import com.example.identityservice.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DatabaseInit implements CommandLineRunner {
    RoleRepository roleRepository;
    AccountRepository accountRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        initRole(RoleEnum.ROLE_ADMIN.name());
        initRole(RoleEnum.ROLE_USER.name());
        initAccount("admin", "admin@example.com", "88888888");
    }

    private void initRole(String roleName) {
        if (roleRepository.findByRoleName(roleName).isEmpty()) {
            roleRepository.save(RoleEntity.builder()
                    .roleName(roleName)
                    .build());
        } else {
            System.out.println("Role " + roleName + " already exists");
        }
    }

    private void initAccount(String username, String email, String password) {
        if (accountRepository.findByUsername(username).isEmpty()) {
            AccountEntity accountEntity = AccountEntity.builder()
                    .username(username)
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .userEntity(UserEntity.builder()
                            .firstName(username)
                            .lastName(username)
                            .fullName(username)
                            .email(email)
                            .build())
                    .build().withDefaultRole();
            accountRepository.save(accountEntity);
        }
    }
}
