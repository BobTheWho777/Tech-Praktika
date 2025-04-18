package com.example.techpraktika.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepo extends JpaRepository<com.example.techpraktika.entity.Supplier, Long> {
}
