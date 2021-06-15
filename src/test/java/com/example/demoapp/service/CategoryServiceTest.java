package com.example.demoapp.service;

import com.example.demoapp.domain.Category;
import com.example.demoapp.domain.Image;
import com.example.demoapp.domain.Size;
import com.example.demoapp.domain.User;
import com.example.demoapp.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository repository;

    @BeforeAll
    static void sayHello() {
        System.out.println("Hello everyone!");
    }

    @Test
    @DisplayName("Test should find by name")
    void shouldFindByName() {
        // given
        Category category = Mockito.mock(Category.class);
        Mockito.doReturn(Optional.of(category)).when(repository).findByName(Mockito.any());

        // When
        Category currentCategory = categoryService.get(category.getName());

        // Then
        Assertions.assertEquals(category, currentCategory);
        Mockito.verify(repository).findByName(category.getName());
    }

    @Test
    @DisplayName("Test should not find by name")
    void shouldNotFindByName() {
        // given
        Category category = new Category(1L, "");
        Mockito.doReturn(null).when(repository).findByName(category.getName());

        Assertions.assertThrows(NullPointerException.class, () -> categoryService.get(""));
    }

    @Test
    void shouldBeNull() {
        // given
        String categoryName = "dummy";
        Category category = new Category(1L, categoryName);
        Mockito.doReturn(category).when(repository).save(category);

        Category currentCategory = categoryService.create(categoryName);

        // then
        Assertions.assertNull(currentCategory);
    }

    @Test
    @DisplayName("Test getUsers with non empty List")
    void testGetAllWithNonEmptyList() {
        // given
        Category category1 = Mockito.mock(Category.class);
        Category category2 = Mockito.mock(Category.class);
        Category category3 = Mockito.mock(Category.class);

        ArrayList<Category> mocked = new ArrayList<>();
        mocked.add(category1);
        mocked.add(category2);
        mocked.add(category3);

        Mockito.when(repository.findAll()).thenReturn(mocked);

        // when
        List<Category> categories = categoryService.list();

        // then
        Assertions.assertNotNull(categories);
        Assertions.assertEquals(mocked.size(), categories.size());
    }


}
