package com.imamcsembstu.spring.boot.jwt.controller;


import com.imamcsembstu.spring.boot.jwt.config.authentication.service.AuthenticationService;
import com.imamcsembstu.spring.boot.jwt.config.authentication.service.CustomUserDetailsService;
import com.imamcsembstu.spring.boot.jwt.config.authentication.service.JwtRefreshTokenService;
import com.imamcsembstu.spring.boot.jwt.dto.request.LoginUserRequestDto;
import com.imamcsembstu.spring.boot.jwt.dto.request.RefreshTokenRequestDto;
import com.imamcsembstu.spring.boot.jwt.dto.request.RegisterUserRequestDto;
import com.imamcsembstu.spring.boot.jwt.dto.response.AuthenticationResponse;
import com.imamcsembstu.spring.boot.jwt.dto.response.LoginUserJwtResponseDto;
import com.imamcsembstu.spring.boot.jwt.model.user.User;
import com.imamcsembstu.spring.boot.jwt.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final JwtRefreshTokenService jwtRefreshTokenService;

    private final CustomUserDetailsService customUserDetailsService;

    public AuthenticationController(AuthenticationService authenticationService, UserService userService, JwtRefreshTokenService jwtRefreshTokenService, CustomUserDetailsService customUserDetailsService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.jwtRefreshTokenService = jwtRefreshTokenService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserRequestDto registerUserDto) {
        User registeredUser = userService.registerUser(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody LoginUserRequestDto loginUserDto) {
        
        LoginUserJwtResponseDto loginResponse = authenticationService.authenticateUser(loginUserDto);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(loginResponse.getToken());
        authenticationResponse.setRefreshToken(jwtRefreshTokenService.generateRefreshToken(loginUserDto.getEmail()));
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/refresh")
    public AuthenticationResponse refresh(@RequestBody @Valid final RefreshTokenRequestDto refreshTokenRequestDto) {
        final String username = jwtRefreshTokenService.validateRefreshTokenAndGetUsername(refreshTokenRequestDto.getRefreshToken());
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(authenticationService.generateAccessToken(username));
        authenticationResponse.setRefreshToken(jwtRefreshTokenService.generateRefreshToken( userDetails.getUsername()));
        return authenticationResponse;

    }
}
