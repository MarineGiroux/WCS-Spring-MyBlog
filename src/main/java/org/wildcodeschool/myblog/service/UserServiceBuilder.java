package org.wildcodeschool.myblog.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.wildcodeschool.myblog.repository.UserRepository;

public class UserServiceBuilder {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceBuilder setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
        return this;
    }

    public UserServiceBuilder setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        return this;
    }

    public UserService createUserService() {
        return new UserService(userRepository, passwordEncoder);
    }
}