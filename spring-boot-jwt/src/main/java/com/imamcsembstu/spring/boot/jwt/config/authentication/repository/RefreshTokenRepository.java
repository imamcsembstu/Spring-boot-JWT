package com.imamcsembstu.spring.boot.jwt.config.authentication.repository;

import com.imamcsembstu.spring.boot.jwt.config.authentication.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken findByTokenAndExpirationTimeAfter(String givenToken, OffsetDateTime now);
}
