package com.example.demoapp.service.impl;

import com.example.demoapp.domain.Role;
import com.example.demoapp.repository.RoleRepository;
import com.example.demoapp.service.RoleService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> getRoles() {
        return repository.findAll();
    }

    @Override
    public Role getRole(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Role not found with role " + id)
        );
    }

    @Override
    public Role insert(Role role) {
        return repository.save(role);
    }
}
