package com.example.identityservice.repository;

import com.example.identityservice.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    @Query(value = "select ac from AccountEntity  ac where ac.username = :username or ac.email= :username")
    Optional<AccountEntity> loadByUsernameOrEmail(@Param("username") String username);

    Optional<AccountEntity> findByUsername(String username);
}
