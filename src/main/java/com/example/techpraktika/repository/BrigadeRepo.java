package com.example.techpraktika.repository;

import com.example.techpraktika.entity.Brigade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrigadeRepo extends JpaRepository<Brigade, Long> {
    List<Brigade> findByNameContainingIgnoreCase(String name);
}
