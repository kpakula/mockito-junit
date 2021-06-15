package com.example.demoapp.service;

import com.example.demoapp.domain.Image;

import java.util.List;

public interface ImageService {
    List<Image> getImages();
    Image getImages(Long id);
    Image insert(Image image);
}
