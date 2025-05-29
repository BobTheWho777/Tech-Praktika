package com.example.techpraktika.impl;

import com.example.techpraktika.entity.Client;
import com.example.techpraktika.repository.ClientRepo;
import com.example.techpraktika.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepo repo;

    public ClientServiceImpl(ClientRepo repo) {
        this.repo = repo;
    }

    public List<Client> findAll() {
        return repo.findAll();
    }

    public Optional<Client> findById(Long id) {
        return repo.findById(id);
    }

    public Client save(Client data) {
        return repo.save(data);
    }

    public void update(Client data) {
        repo.save(data);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Client> findByNameContainingIgnoreCase(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

}