package com.imamcsembstu.spring.boot.jwt.config.authentication.service;

import com.imamcsembstu.spring.boot.jwt.config.authentication.CustomUserDetails;
import com.imamcsembstu.spring.boot.jwt.model.user.User;
import com.imamcsembstu.spring.boot.jwt.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
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
