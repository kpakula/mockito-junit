package com.example.demoapp.service;

import com.example.demoapp.domain.User;
import com.example.demoapp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;

    @Test
    @DisplayName("Test getUserById")
    void testFindById() {
        // given
        User user = new User(1L, "Login", "Password", "FirstName", "LastName", "email@email.com", false);
        Mockito.doReturn(Optional.of(user)).when(repository).findById(1L);

        // When
        User currentUser = service.getUserById(1L);

        // Then
        Assertions.assertTrue(Objects.nonNull(currentUser), "User is null");
        Assertions.assertSame(user, currentUser, "The user returned was not the same as the mock");
    }

    @Test
    @DisplayName("Test getUsers with empty List")
    void testGetAll() {
        // given
        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());

        // when
        List<User> users = service.getUsers();

        // then
        Assertions.assertNotNull(users);
        Assertions.assertEquals(0, users.size());
    }

    @Test
    @DisplayName("Test getUsers with non empty List")
    void testGetAllWithNonEmptyList() {
        // given
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);

        ArrayList<User> mocked = new ArrayList<>();
        mocked.add(user1);
        mocked.add(user2);

        Mockito.when(repository.findAll()).thenReturn(mocked);

        // when
        List<User> users = service.getUsers();

        // then
        Assertions.assertNotNull(users);
        Assertions.assertEquals(mocked.size(), users.size());
    }

    @Test
    @DisplayName("Should insert user into repository")
    void shouldInsertUserInto() {
        // given
        User user = Mockito.mock(User.class);

        // when
        service.insert(user);

        // then
        Mockito.verify(repository).save(user);
    }

    @Test
    public void shouldThrowExceptionWhenUserNotExist() {
        // given
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        // when - then
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.getUserById(1L));
    }

    @Test
    public void shouldThrowExceptionWhenUpdatingNotExistingUser() {
        // given
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        // when - then
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.updateUser(1L, Mockito.mock(User.class)));
    }
}
