package com.example.techpraktika.service;

import com.example.techpraktika.entity.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    List<Supplier> findAll();

    Optional<Supplier> findById(Long id);

    Supplier save(Supplier data);

    void update(Supplier data);

    void deleteById(Long id);
}
