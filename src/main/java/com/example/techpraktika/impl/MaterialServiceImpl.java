package com.example.techpraktika.impl;

import com.example.techpraktika.entity.Material;
import com.example.techpraktika.repository.MaterialRepo;
import com.example.techpraktika.service.MaterialService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepo repo;

    public MaterialServiceImpl(MaterialRepo repo) {this.repo = repo;}

    public List<Material> findAll() {
        return repo.findAll();
    }


    public Optional<Material> findById(Long id) {
        return repo.findById(id);
    }


    public Material save(Material data) {
        return repo.save(data);
    }


    public void update(Material data) {
        repo.save(data);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
