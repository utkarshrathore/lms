package com.aspire.lms.controller;

import com.aspire.lms.exception.EntityNotFoundException;
import com.aspire.lms.model.User;
import com.aspire.lms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(
            @PathVariable(value = "id") Long userId)
            throws EntityNotFoundException {
        return userService.getUserById(userId);
    }
}
