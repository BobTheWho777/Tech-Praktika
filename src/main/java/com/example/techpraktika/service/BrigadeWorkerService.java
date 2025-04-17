package com.example.techpraktika.service;

import com.example.techpraktika.entity.BrigadeWorker;

import java.util.List;
import java.util.Optional;

public interface BrigadeWorkerService {
    List<BrigadeWorker> findAll();

    Optional<BrigadeWorker> findById(Long id);

    BrigadeWorker save(BrigadeWorker data);

    void update(BrigadeWorker data);

    void deleteById(Long id);
}
