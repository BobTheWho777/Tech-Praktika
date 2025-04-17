package com.example.techpraktika.impl;

import com.example.techpraktika.entity.Supplier;
import com.example.techpraktika.repository.SupplierRepo;
import com.example.techpraktika.service.SupplierService;

import java.util.List;
import java.util.Optional;

public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepo repo;

    public SupplierServiceImpl(SupplierRepo repo) {this.repo = repo;   }

    public List<Supplier> findAll() {
        return repo.findAll();
    }

    public Optional<Supplier> findById(Long id) {
        return repo.findById(id);
    }

    public Supplier save(Supplier data) {
        return repo.save(data);
    }

    public void update(Supplier data) {
        repo.save(data);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
