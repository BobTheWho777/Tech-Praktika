package com.example.techpraktika.impl;

import com.example.techpraktika.entity.ConstructionSite;
import com.example.techpraktika.repository.ConstructionSiteRepo;
import com.example.techpraktika.service.ConstructionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConstructionSiteServiceImpl implements ConstructionService {
    private final ConstructionSiteRepo repo;

    public ConstructionSiteServiceImpl(ConstructionSiteRepo repo) {this.repo = repo;}

    public List<ConstructionSite> findAll() {return repo.findAll();}

    public Optional<ConstructionSite> findById(Long id) {return repo.findById(id);}

    public ConstructionSite save(ConstructionSite data) {return repo.save(data);}

    public void update(ConstructionSite data) {repo.save(data);}

    public void deleteById(Long id) {repo.deleteById(id);}
}
