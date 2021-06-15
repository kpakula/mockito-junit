package com.example.demoapp.controller;

import com.example.demoapp.domain.User;
import com.example.demoapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User currentUser = userService.insert(user);
        return new ResponseEntity<>(currentUser, HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}