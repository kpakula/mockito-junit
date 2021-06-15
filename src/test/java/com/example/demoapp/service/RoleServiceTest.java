package com.example.demoapp.service;

import com.example.demoapp.domain.Role;
import com.example.demoapp.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
public class RoleServiceTest {

    @Autowired
    private RoleService service;

    @MockBean
    private RoleRepository repository;

    private Role role;

    @BeforeEach
    public void beforeEach() {
        this.role = Mockito.mock(Role.class);
    }


    @Test
    void shouldNotFindRoleById() {
        // given
        Mockito.doReturn(Optional.of(role)).when(repository).findById(1L);

        // when - then
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.getRole(2L));
    }

    @Test
    void shouldFindRoleById() {
        // given
        Mockito.doReturn(Optional.of(role)).when(repository).findById(2L);

        // when
        Role currentRole = service.getRole(2L);

        // Then
        Assertions.assertTrue(Objects.nonNull(currentRole));
        Assertions.assertEquals(role, currentRole);
    }

    @Test
    void shouldBeAllRoles() {
        // given
        Role role = Mockito.mock(Role.class);

        ArrayList<Role> mocked = new ArrayList<>();
        mocked.add(role);

        Mockito.when(repository.findAll()).thenReturn(mocked);

        // when
        List<Role> roles = service.getRoles();

        // then
        Assertions.assertNotNull(roles);
        Assertions.assertEquals(mocked.size(), roles.size());
    }

    @Test
    void shouldInsertRole() {
        // given
        Role temporaryRole = Mockito.mock(Role.class);

        // when
        service.insert(temporaryRole);

        // then
        Mockito.verify(repository).save(temporaryRole);
    }
}
