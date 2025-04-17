package com.example.techpraktika.service;

import com.example.techpraktika.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> findAll();

    Optional<Client> findById(Long id);

    Client save(Client data);

    void update(Client data);

    void deleteById(Long id);
}
