package com.imamcsembstu.spring.boot.jwt.service;

import com.imamcsembstu.spring.boot.jwt.config.authentication.CustomUserDetails;
import com.imamcsembstu.spring.boot.jwt.dto.request.RegisterUserRequestDto;
import com.imamcsembstu.spring.boot.jwt.dto.response.RegisterUserResponseDto;
import com.imamcsembstu.spring.boot.jwt.model.User;
import com.imamcsembstu.spring.boot.jwt.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;

        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(RegisterUserRequestDto request) {

        User user = convertToEntity(request, new User());
        repository.save(user);
        return user;
    }

    private User convertToEntity(RegisterUserRequestDto request, User user) {
        String email = request.getEmail();
        Optional<User> existingUser = repository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with the email address " + email + " already exists.");
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        user.setEmail(request.getEmail().trim().toLowerCase());
        user.setPassword(hashedPassword);
        user.setFullName(request.getFullName());
        return user;
    }


    public RegisterUserResponseDto getSingleById(Long id) {
        User user  = findById(id);
        if (Objects.nonNull(user)){
            return convertToResponseDto(user);
        }else {
            throw new RuntimeException("User Not Found");
        }
    }

    private RegisterUserResponseDto convertToResponseDto(User user) {
        return RegisterUserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .build();
    }

    public User findById(Long id) {
        if (id != null) {
            return repository.findById(id).orElse(null);
        } else {
            return null;
        }
    }

    public User findByUserName(String userName) {
        if (userName != null) {
            return repository.findByEmail(userName).orElse(null);
        } else {
            return null;
        }

    }
}
