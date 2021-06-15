package com.example.demoapp.service.impl;

import com.example.demoapp.domain.Image;
import com.example.demoapp.repository.ImageRepository;
import com.example.demoapp.service.ImageService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Image> getImages() {
        return imageRepository.findAll();
    }

    @Override
    public Image getImages(Long id) {
        return imageRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found with userId " + id)
        );
    }

    @Override
    public Image insert(Image image) {
        if (image.getId() == 0L) throw new RuntimeException();
        return imageRepository.save(image);
    }
}
