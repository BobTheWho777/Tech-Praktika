package com.example.techpraktika.service;

import com.example.techpraktika.entity.Material;

import java.util.List;
import java.util.Optional;

public interface MaterialService {
    List<Material> findAll();

    Optional<Material> findById(Long id);

    Material save(Material data);

    void update(Material data);

    void deleteById(Long id);

    List<Material> findByNameContainingIgnoreCase(String name);
}
