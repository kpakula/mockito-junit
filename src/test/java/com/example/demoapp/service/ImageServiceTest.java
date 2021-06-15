package com.example.demoapp.service;

import com.example.demoapp.domain.Image;
import com.example.demoapp.domain.Size;
import com.example.demoapp.repository.ImageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Objects;
import java.util.Optional;

@SpringBootTest
public class ImageServiceTest {

    @Autowired
    private ImageService service;

    @MockBean
    private ImageRepository repository;

    private Image image;

    @BeforeEach
    public void beforeEach() {
        this.image = Mockito.mock(Image.class);
    }

    @AfterEach
    public void afterEach() {
        this.image = null;
    }

    @Test
    void shouldFindImageById() {
        // given
        Mockito.doReturn(Optional.of(image)).when(repository).findById(1L);

        // when
        Image currentImage = service.getImages(1L);

        // Then
        Assertions.assertTrue(Objects.nonNull(currentImage));
        Assertions.assertEquals(image, currentImage);
    }

    @Test
    void shouldNotFindImageById() {
        // given
        Mockito.doReturn(Optional.of(image)).when(repository).findById(1L);

        // when - then
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.getImages(2L));
    }

    @Test
    void shouldNotFindImageButWrongArgumentException() {
        // given
        Image temporaryImage = new Image(0L, "A", "B", "C", new Size(1L, 5, 5));

        // when - then
        Assertions.assertThrows(RuntimeException.class, () -> service.insert(temporaryImage));
    }

    @Test
    void shouldInsertImageOnce() {
        // given
        Image temporaryImage = new Image(1L, "A", "B", "C", new Size(1L, 5, 5));

        // when
        service.insert(temporaryImage);

        // then
        Mockito.verify(repository).save(temporaryImage);
    }
}
