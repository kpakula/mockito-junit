package com.example.demoapp.controller;

import com.example.demoapp.domain.Image;
import com.example.demoapp.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> image = imageService.getImages();
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Image> getImage(@PathVariable Long id) {
        return new ResponseEntity<>(imageService.getImages(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Image> saveImage(@RequestBody Image image) {
        Image currentImage = imageService.insert(image);
        return new ResponseEntity<>(currentImage, HttpStatus.CREATED);
    }

}