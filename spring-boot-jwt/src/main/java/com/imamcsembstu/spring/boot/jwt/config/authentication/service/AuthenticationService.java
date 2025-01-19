package com.imamcsembstu.spring.boot.jwt.config.authentication.service;

import com.imamcsembstu.spring.boot.jwt.config.authentication.CustomUserDetails;
import com.imamcsembstu.spring.boot.jwt.config.authentication.jwt.JwtHelper;
import com.imamcsembstu.spring.boot.jwt.dto.request.LoginUserRequestDto;
import com.imamcsembstu.spring.boot.jwt.dto.response.LoginUserJwtResponseDto;
import com.imamcsembstu.spring.boot.jwt.dto.response.RefreshTokenAuthenticationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;
    private final JwtRefreshTokenService jwtRefreshTokenService;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtHelper jwtHelper, JwtRefreshTokenService jwtRefreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtHelper = jwtHelper;
        this.jwtRefreshTokenService = jwtRefreshTokenService;
    }

    public LoginUserJwtResponseDto authenticateUser(LoginUserRequestDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail().trim().toLowerCase(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String jwtToken = generateAccessToken(userDetails.getUsername());

        return LoginUserJwtResponseDto.builder()
                .token(jwtToken)
                .email(userDetails.getUsername())
                .build();
    }

    public String generateAccessToken(String userName) {
        return jwtHelper.generateToken(userName);
    }

}
