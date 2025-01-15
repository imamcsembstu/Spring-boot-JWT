package com.imamcsembstu.spring.boot.jwt.controller;


import com.imamcsembstu.spring.boot.jwt.config.authentication.service.AuthenticationService;
import com.imamcsembstu.spring.boot.jwt.dto.request.LoginUserRequestDto;
import com.imamcsembstu.spring.boot.jwt.dto.request.RegisterUserRequestDto;
import com.imamcsembstu.spring.boot.jwt.dto.response.LoginUserJwtResponseDto;
import com.imamcsembstu.spring.boot.jwt.model.User;
import com.imamcsembstu.spring.boot.jwt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserRequestDto registerUserDto) {
        User registeredUser = userService.registerUser(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserJwtResponseDto> authenticate(@RequestBody LoginUserRequestDto loginUserDto) {
        
        LoginUserJwtResponseDto loginResponse = authenticationService.authenticateUser(loginUserDto);
        return ResponseEntity.ok(loginResponse);
    }
}
