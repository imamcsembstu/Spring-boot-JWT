package com.imamcsembstu.spring.boot.jwt.config.authentication.service;

import com.imamcsembstu.spring.boot.jwt.config.authentication.model.RefreshToken;
import com.imamcsembstu.spring.boot.jwt.config.authentication.repository.RefreshTokenRepository;
import com.imamcsembstu.spring.boot.jwt.dto.response.RefreshTokenAuthenticationResponse;
import com.imamcsembstu.spring.boot.jwt.model.User;
import com.imamcsembstu.spring.boot.jwt.service.UserService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.UUID;
@Service
public class JwtRefreshTokenService {
    private static final Duration REFRESH_TOKEN_VALIDITY = Duration.ofMinutes(60);
    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;

    public JwtRefreshTokenService(UserService userService, RefreshTokenRepository refreshTokenRepository) {
        this.userService = userService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    // ...

    public String generateRefreshToken(final String userName) {
        final User user = userService.findByUserName(userName);
        final String newToken = UUID.randomUUID().toString();
        final RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(newToken);
        refreshToken.setExpirationTime(OffsetDateTime.now().plus(REFRESH_TOKEN_VALIDITY));
        refreshToken.setUser(user);
        refreshTokenRepository.save(refreshToken);

        return newToken;

    }

    public String validateRefreshTokenAndGetUsername(final String givenToken) {
        final RefreshToken refreshToken = refreshTokenRepository.findByTokenAndExpirationTimeAfter(givenToken, OffsetDateTime.now());
        if (refreshToken == null) {
//            log.warn("refresh token invalid");
            throw new RuntimeException("Not Valid");
        }
        return refreshToken.getUser().getEmail();
    }

}