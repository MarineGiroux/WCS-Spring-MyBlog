package org.wildcodeschool.myblog.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.stream.Collectors;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
    private final Long id;

    public CustomUserDetails(User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles().stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        this.id = user.getId();
    }

    public Long getId() {
        return id;
    }
}
