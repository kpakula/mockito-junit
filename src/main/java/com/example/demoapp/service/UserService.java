package com.example.demoapp.service;

import com.example.demoapp.domain.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserById(Long id);
    User insert(User user);
    void updateUser(Long id, User user);
    void deleteUser(Long id);
}
