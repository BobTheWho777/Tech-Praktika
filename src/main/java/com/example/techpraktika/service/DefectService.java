package com.example.techpraktika.service;

import com.example.techpraktika.entity.Defect;

import java.util.List;
import java.util.Optional;

public interface DefectService {
    List<Defect> findAll();

    Optional<Defect> findById(Long id);

    Defect save(Defect data);

    void update(Defect data);

    void deleteById(Long id);
}
