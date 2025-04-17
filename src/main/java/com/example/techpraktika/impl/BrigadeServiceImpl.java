package com.example.techpraktika.impl;

import com.example.techpraktika.entity.Brigade;
import com.example.techpraktika.repository.BrigadeRepo;
import com.example.techpraktika.service.BrigadeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrigadeServiceImpl implements BrigadeService {
    private final BrigadeRepo repo;

    public BrigadeServiceImpl(BrigadeRepo repo) {this.repo = repo;}

    public List<Brigade> findAll(){return repo.findAll();}

    public Optional<Brigade> findById(Long id){ return repo.findById(id);}

    public Brigade save (Brigade data){return repo.save(data);}

    public void update (Brigade data) {repo.save(data);}

    public void deleteById(Long id){repo.deleteById(id);}

}