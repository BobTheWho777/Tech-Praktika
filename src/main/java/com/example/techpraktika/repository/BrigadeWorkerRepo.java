package com.example.techpraktika.repository;

import com.example.techpraktika.entity.BrigadeWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrigadeWorkerRepo extends JpaRepository<BrigadeWorker, Long> {
}
