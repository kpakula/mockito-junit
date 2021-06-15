package com.example.demoapp.service;

import com.example.demoapp.domain.Size;

public interface SizeService {
    Size getSize(Long id);
    Size resizeX(Size size, int x);
    Size resizeY(Size size, int y);
    Size insert(Size size);
}
