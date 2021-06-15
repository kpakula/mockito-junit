package com.example.demoapp.service.impl;

import com.example.demoapp.domain.User;
import com.example.demoapp.repository.UserRepository;
import com.example.demoapp.service.UserService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found with userId " + id)
        );
    }

    @Override
    public User insert(User user) {
        return userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, User user) {
        User userFromDb = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id " + id)
        );

        userFromDb.setLogin(user.getLogin());
        userFromDb.setEmail(user.getEmail());
        userFromDb.setPassword(user.getPassword());
        userFromDb.setFirstName(user.getFirstName());
        userFromDb.setLastName(user.getLastName());

        userRepository.save(userFromDb);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
