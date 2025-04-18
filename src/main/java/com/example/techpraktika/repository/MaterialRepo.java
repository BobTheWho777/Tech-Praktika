package com.example.techpraktika.repository;

import com.example.techpraktika.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepo extends JpaRepository<Material, Long> {
}
