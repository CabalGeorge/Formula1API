package com.api.formula1.service;

import com.api.formula1.model.user.User;
import com.api.formula1.repository.internal.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // This is just a mock implementation to make sure that we have some preregistered users for testing purposes
    public void insertUsers(List<User> users) {
        if(userRepository.findAll().isEmpty()) {
            userRepository.saveAll(users);
        }
    }
}
