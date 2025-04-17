package com.example.techpraktika.service;

import com.example.techpraktika.entity.ConstructionSite;

import java.util.List;
import java.util.Optional;

public interface ConstructionService {
    List<ConstructionSite> findAll();

    Optional<ConstructionSite> findById(Long id);

    ConstructionSite save(ConstructionSite data);

    void update(ConstructionSite data);

    void deleteById(Long id);
}
