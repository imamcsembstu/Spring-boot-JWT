package com.imamcsembstu.spring.boot.jwt.controller;

import com.imamcsembstu.spring.boot.jwt.dto.response.RegisterUserResponseDto;
import com.imamcsembstu.spring.boot.jwt.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{id}")
    public RegisterUserResponseDto getSingleById(@PathVariable Long id) {

        return userService.getSingleById(id);
    }
}
