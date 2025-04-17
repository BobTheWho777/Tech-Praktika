package com.example.techpraktika.service;

import com.example.techpraktika.entity.SiteStage;

import java.util.List;
import java.util.Optional;

public interface SiteStageService {
    List<SiteStage> findAll();

    Optional<SiteStage> findById(Long id);

    SiteStage save(SiteStage data);

    void update(SiteStage data);

    void deleteById(Long id);
}
