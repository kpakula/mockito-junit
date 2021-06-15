package com.example.demoapp.repository;

import com.example.demoapp.domain.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size, Long> {
}
