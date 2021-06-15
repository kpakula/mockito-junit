package com.example.demoapp.service;

import com.example.demoapp.domain.Category;

import java.util.List;

public interface CategoryService {
    public Category get(String name);

    public Category get(Long id);

    public List<Category> list();

    public Category create(String categoryName);

    public Category edit(Category category, String name);

    public void delete(Category category);
}
