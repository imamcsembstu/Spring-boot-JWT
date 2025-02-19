package com.imamcsembstu.spring.boot.jwt.config.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imamcsembstu.spring.boot.jwt.model.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;


public class CustomUserDetails implements UserDetails {
    private static final String ERROR_MESSAGE = "Your role has not been assigned by the Administrator. Please contact the administrator for role assignment.";

    private final Long id;

    private final String email;

    private final String fullName;

    @JsonIgnore
    private final String password;

    private final Long roleId;

    private final String role;

    public CustomUserDetails(Long id, String email, String fullName, String password, Long roleId, String role) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.roleId = roleId;
        this.role = role;
    }

    public static CustomUserDetails build(User user) {

        if(Objects.isNull(user.getRole())){
            throw new RuntimeException(ERROR_MESSAGE);
        }

        return new CustomUserDetails(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getPassword(),
                user.getRole().getId(),
                user.getRole().getName());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Long getRoleId() {
        return roleId;
    }
    public String getRole() {
        return role;
    }
}
