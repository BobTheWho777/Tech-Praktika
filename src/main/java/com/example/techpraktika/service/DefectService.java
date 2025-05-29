package com.example.techpraktika.service;

import com.example.techpraktika.entity.Defect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DefectService {
    List<Defect> findByDescriptionContaining(String description);
    Page<Defect> findByDescriptionContaining(String description, Pageable pageable);
    List<Defect> findAll();
    Page<Defect> findAll(Pageable pageable);
    Optional<Defect> findById(Long id);
    Defect save(Defect data);
    void update(Defect data);
    void deleteById(Long id);
}