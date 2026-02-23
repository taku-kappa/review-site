package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "review_ratings",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_review_ratings_review_user",
                        columnNames = {"review_id", "user_id"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)     // JPAからだけコンストラクタを作成できる @AllArgsConstructor・@Builderがあるとデフォルトコンストラタが生成されないためこれを記述しないといけない
@AllArgsConstructor     // 全フィールド変数を引数にして初期化するコンストラク
@Builder // .builder() ~ .build();でnewできる
public class ReviewRatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "review_id", nullable = false)
    private Long reviewId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Integer rating; // 1〜5

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
