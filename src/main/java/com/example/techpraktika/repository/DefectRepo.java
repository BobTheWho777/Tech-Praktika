package com.example.techpraktika.repository;

import com.example.techpraktika.entity.Defect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefectRepo extends JpaRepository<Defect, Long> {
}
