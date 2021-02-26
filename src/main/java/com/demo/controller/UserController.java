package com.demo.controller;

import com.demo.exception.ResourceNotFoundException;
import com.demo.model.User;
import com.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        Logger logger =
                LoggerFactory.getLogger(UserController.class);
        logger.info("TEST");
        User user = userService.getUserById(id);
        if (user == null) {
            throw new ResourceNotFoundException();
        }
        return user;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public boolean addUser(@RequestBody @Valid User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }

    @PatchMapping
    public boolean updateUser(@RequestBody @Valid User user) {
        return userService.updateUser(user);
    }

}
