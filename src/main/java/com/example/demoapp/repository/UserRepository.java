package com.example.demoapp.repository;

import com.example.demoapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
        List<User> findByLastName(String lastName);
        List<User> findByFirstName(String firstName);
}
