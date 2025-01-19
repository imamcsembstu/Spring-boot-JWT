package com.imamcsembstu.spring.boot.jwt.config.authentication.model;

import com.imamcsembstu.spring.boot.jwt.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.OffsetDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RefreshToken {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private OffsetDateTime expirationTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

