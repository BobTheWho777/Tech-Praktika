package com.example.techpraktika.repository;

import com.example.techpraktika.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    List<Client> findByNameContainingIgnoreCase(String name);
}
