package com.example.demoapp.service.impl;

import com.example.demoapp.domain.Category;
import com.example.demoapp.repository.CategoryRepository;
import com.example.demoapp.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category get(String name) {
        return categoryRepository.findByName(name).orElse(null);
    }

    public Category get(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public List<Category> list() {
        return categoryRepository.findAll();
    }

    public Category create(String categoryName) {
        Category newCategory = new Category();
        newCategory.setName(categoryName);
        return categoryRepository.save(newCategory);
    }

    public Category edit(Category category, String name) {
        category.setName(name);
        return categoryRepository.save(category);
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
    }
}
