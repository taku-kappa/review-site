package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "follows",
        uniqueConstraints = {     // UNIQUE制約（この値の組み合わせは1回しか登録できないという制約）
                @UniqueConstraint(
                        name = "uk_follows_follower_followed",     // 制約の名前
                        columnNames = {"follower_user_id", "followed_user_id"}     // UNIQUEをかける「対象カラム」
                )
        }
)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)     // JPAからだけコンストラクタを作成できる @AllArgsConstructor・@Builderがあるとデフォルトコンストラタが生成されないためこれを記述しないといけない
@AllArgsConstructor     // 全フィールド変数を引数にして初期化するコンストラク
@Builder // .builder() ~ .build();でnewできる
public class FollowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "follower_user_id", nullable = false)
    private Long followerUserId;

    @Column(name = "followed_user_id", nullable = false)
    private Long followedUserId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist     // INSERT前に自動実行される（ライフサイクルコールバック）
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
