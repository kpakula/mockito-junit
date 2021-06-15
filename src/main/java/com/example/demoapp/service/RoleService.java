package com.example.demoapp.service;

import com.example.demoapp.domain.Role;

import java.util.List;

public interface RoleService {

        public List<Role> getRoles();

        public Role getRole(Long id);

        public Role insert(Role role);
}
