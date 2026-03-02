package com.example.backend.repository;

import com.example.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository
        extends JpaRepository<UserEntity, Long> {

    // ログイン用
    Optional<UserEntity> findByEmail(String email);

    // 登録の重複チェック用
    boolean existsByEmail(String email);
}
