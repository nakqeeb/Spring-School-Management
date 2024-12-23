package com.nakqeeb.sms.service;

import com.nakqeeb.sms.dao.UserRepository;
import com.nakqeeb.sms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public boolean isUserExist(String email) {
        return this.findUserByEmail(email).isPresent();
    }
}
