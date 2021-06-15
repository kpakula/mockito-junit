package com.example.demoapp.repository;

import com.example.demoapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAll();
    Optional<Category> findByName(String name);
    Set<Category> findAllByNameIn(Collection<String> names);
}