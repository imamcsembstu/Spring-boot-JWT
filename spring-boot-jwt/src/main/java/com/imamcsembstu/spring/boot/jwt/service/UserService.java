package com.imamcsembstu.spring.boot.jwt.service;

import com.imamcsembstu.spring.boot.jwt.config.authentication.CustomUserDetails;
import com.imamcsembstu.spring.boot.jwt.dto.request.RegisterUserRequestDto;
import com.imamcsembstu.spring.boot.jwt.model.User;
import com.imamcsembstu.spring.boot.jwt.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public User findByUserName(String email) {
        Optional<User> user = repository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        }
        else throw new RuntimeException("Not found");
    }


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(email);

        if (user.isPresent()) {
            return CustomUserDetails.build(user.get());
        } else {
            throw new UsernameNotFoundException("User Not Found" + email);
        }
    }
}
