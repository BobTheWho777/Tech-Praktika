package com.example.techpraktika.repository;

import com.example.techpraktika.entity.BrigadeWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrigadeWorkerRepo extends JpaRepository<BrigadeWorker, Long> {
    List<BrigadeWorker> findByBrigadeId(Long brigadeId);
}
