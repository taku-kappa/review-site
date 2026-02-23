package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)     // JPAからだけコンストラクタを作成できる @AllArgsConstructor・@Builderがあるとデフォルトコンストラタが生成されないためこれを記述しないといけない
@AllArgsConstructor     // 全フィールド変数を引数にして初期化するコンストラク
@Builder // .builder() ~ .build();でnewできる
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist     // INSERT前に自動実行される（ライフサイクルコールバック）
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate     // UPDATE前に自動実行される（ライフサイクルコールバック）
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
