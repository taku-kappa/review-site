package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)     // JPAからだけコンストラクタを作成できる @AllArgsConstructor・@Builderがあるとデフォルトコンストラタが生成されないためこれを記述しないといけない
@AllArgsConstructor     // 全フィールド変数を引数にして初期化するコンストラク
@Builder // .builder() ~ .build();でnewできる
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "target_type", length = 50)
    private String targetType;

    @Column(name = "target_id")
    private Long targetId;

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
