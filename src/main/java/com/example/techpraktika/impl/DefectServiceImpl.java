package com.example.techpraktika.impl;

import com.example.techpraktika.entity.Defect;
import com.example.techpraktika.repository.DefectRepo;
import com.example.techpraktika.service.DefectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefectServiceImpl implements DefectService {
    private final DefectRepo repo;

    public DefectServiceImpl(DefectRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<Defect> findByDescriptionContaining(String description) {
        return repo.findByDescriptionContainingIgnoreCase(description);
    }


    public Page<Defect> findByDescriptionContaining(String description, Pageable pageable) {
        return repo.findByDescriptionContainingIgnoreCase(description, pageable);
    }

    @Override
    public List<Defect> findAll() {
        return repo.findAll();
    }


    public Page<Defect> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Optional<Defect> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Defect save(Defect data) {
        return repo.save(data);
    }

    @Override
    public void update(Defect data) {
        repo.save(data);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}