package com.example.techpraktika.impl;

import com.example.techpraktika.entity.BrigadeWorker;
import com.example.techpraktika.repository.BrigadeWorkerRepo;
import com.example.techpraktika.service.BrigadeWorkerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrigadeWorkerServiceImpl implements BrigadeWorkerService {
    private final BrigadeWorkerRepo repo;

    public BrigadeWorkerServiceImpl(BrigadeWorkerRepo repo) {this.repo = repo;}

    public List<BrigadeWorker> findAll() {return repo.findAll();}

    public Optional<BrigadeWorker> findById(Long id) {return repo.findById(id);}

    public BrigadeWorker save(BrigadeWorker data) {return repo.save(data);}

    public void update(BrigadeWorker data) { repo.save(data);}

    public void deleteById(Long id) { repo.deleteById(id);}

    public List<BrigadeWorker> findByBrigadeId(Long brigadeId) {return repo.findByBrigadeId(brigadeId);}

}
