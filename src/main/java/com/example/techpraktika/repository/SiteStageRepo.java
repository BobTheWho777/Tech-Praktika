package com.example.techpraktika.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteStageRepo extends JpaRepository<com.example.techpraktika.entity.SiteStage, Long> {
}
