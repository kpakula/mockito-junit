package com.example.demoapp.service;

import com.example.demoapp.domain.Size;
import com.example.demoapp.repository.SizeRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Objects;
import java.util.Optional;

@SpringBootTest
public class SizeServiceTest {
    @Autowired
    private SizeService service;

    @MockBean
    private SizeRepository repository;

    private Size size;

    @AfterAll
    static void sayBye() {
        System.out.println("Bye Bye!");
    }

    @BeforeEach
    public void beforeEach() {
        this.size = Mockito.mock(Size.class);
    }

    @Test
    void shouldFindSizeById() {
        // given
        Mockito.doReturn(Optional.of(size)).when(repository).findById(Mockito.any());

        // when
        Size currentSize = service.getSize(1L);

        // Then
        Assertions.assertTrue(Objects.nonNull(currentSize));
        Assertions.assertEquals(size, currentSize);
    }

    @Test
    @DisplayName("Test resize by X")
    void shouldResizeByX() {
        // given
        Mockito.doReturn(size).when(repository).save(size);

        // When
        service.resizeX(size, 12);

        // Then
        Mockito.verify(repository).save(size);
    }

    @Test
    @DisplayName("Test resize by Y")
    void shouldResizeByY() {
        // given
        Mockito.doReturn(size).when(repository).save(size);

        // When
        service.resizeX(size, 12);

        // Then
        Mockito.verify(repository).save(size);
    }
}
