package com.example.techpraktika.impl;

import com.example.techpraktika.entity.SiteStage;
import com.example.techpraktika.repository.SiteStageRepo;
import com.example.techpraktika.service.SiteStageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SiteStageServiceIml implements SiteStageService {
    private final SiteStageRepo repo;

    public SiteStageServiceIml(SiteStageRepo repo) {this.repo = repo;}

    public List<SiteStage> findAll() {return repo.findAll();}

    public List<SiteStage> findByNameContaining(String name) {return repo.findByNameContainingIgnoreCase(name);}

    public Optional<SiteStage> findById(Long id) {
        return repo.findById(id);
    }

    public SiteStage save(SiteStage data) {
        return repo.save(data);
    }

    public void update(SiteStage data) {
        repo.save(data);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
