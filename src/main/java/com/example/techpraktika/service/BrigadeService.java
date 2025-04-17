package com.example.techpraktika.service;

import com.example.techpraktika.entity.Brigade;

import java.util.List;
import java.util.Optional;

public interface BrigadeService {

    List<Brigade> findAll();

    Optional<Brigade> findById(Long id);

    Brigade savve(Brigade data);

    void update(Brigade data);

    void deleteById(Long id);

}
