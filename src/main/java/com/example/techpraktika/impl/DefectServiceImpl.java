package com.example.techpraktika.impl;

import com.example.techpraktika.entity.Defect;
import com.example.techpraktika.repository.DefectRepo;
import com.example.techpraktika.service.DefectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DefectServiceImpl implements DefectService {
    private final DefectRepo repo;

    public DefectServiceImpl(DefectRepo repo) {this.repo = repo;}

    public List<Defect> findAll() {return repo.findAll();}

    public Optional<Defect> findById(Long id) {return repo.findById(id);}

    public Defect save(Defect data) {return repo.save(data);}

    public void update(Defect data) {repo.save(data);}

    public void deleteById(Long id) {repo.deleteById(id);}
}
