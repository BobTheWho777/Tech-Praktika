package com.example.techpraktika.repository;

import com.example.techpraktika.entity.Defect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DefectRepo extends JpaRepository<Defect, Long> {
    List<Defect> findByDescriptionContainingIgnoreCase(String description);
    Page<Defect> findByDescriptionContainingIgnoreCase(String description, Pageable pageable);
}