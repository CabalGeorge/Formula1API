package com.api.formula1;

import com.api.formula1.model.user.User;
import com.api.formula1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Formula1Application implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(Formula1Application.class, args);
    }

    @Override
    public void run(String... args) {
        // Add some predefined users so the application can be tested
        List<User> users = List.of(
                buildUser(1L, "user1"),
                buildUser(2L, "user2"),
                buildUser(3L, "user3"),
                buildUser(4L, "user4"),
                buildUser(5L, "user5")
        );
        userService.insertUsers(users);
    }

    private User buildUser(Long id, String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setUserBalance(100L);
        return user;
    }
}
