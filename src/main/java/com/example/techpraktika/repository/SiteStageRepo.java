package com.example.techpraktika.repository;

import com.example.techpraktika.entity.SiteStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteStageRepo extends JpaRepository<com.example.techpraktika.entity.SiteStage, Long> {
    List<SiteStage> findByNameContainingIgnoreCase(String name);
}
