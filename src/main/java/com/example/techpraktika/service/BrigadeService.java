package com.example.techpraktika.service;

import com.example.techpraktika.entity.Brigade;
import com.example.techpraktika.entity.ConstructionSite;

import java.util.List;
import java.util.Optional;

public interface BrigadeService {
    List<Brigade> findAll();
    Optional<Brigade> findById(Long id);
    List<Brigade> findByNameContaining(String search);
    void save(Brigade brigade);
    void update(Brigade brigade);
    void deleteById(Long id);
    void addConstructionSite(Long brigadeId, Long siteId);
    void removeConstructionSite(Long brigadeId, Long siteId);
}
