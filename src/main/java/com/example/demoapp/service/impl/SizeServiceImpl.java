package com.example.demoapp.service.impl;

import com.example.demoapp.domain.Image;
import com.example.demoapp.domain.Size;
import com.example.demoapp.repository.SizeRepository;
import com.example.demoapp.service.SizeService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;

    public SizeServiceImpl(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }
    @Override
    public Size getSize(Long id) {
        return this.sizeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Size id not found")
        );
    }

    @Override
    public Size resizeX(Size size, int x) {
        size.setX(x);
        return sizeRepository.save(size);
    }

    @Override
    public Size resizeY(Size size, int y) {
        size.setY(y);
        return sizeRepository.save(size);
    }

    @Override
    public Size insert(Size size) {
        return sizeRepository.save(size);
    }
}
