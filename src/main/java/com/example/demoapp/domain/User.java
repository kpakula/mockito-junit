package com.example.demoapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long id;

    @Column(name = "login", length = 50, unique = true)
    private String login;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "firstName", length = 50)
    private String firstName;

    @Column(name = "lastName", length = 50)
    private String lastName;

    @Column(name = "email", length = 254, unique = true)
    private String email;

    @Column(nullable = false)
    private boolean activated = false;
}
