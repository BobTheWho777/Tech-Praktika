package com.example.techpraktika.controller;

import com.example.techpraktika.entity.Brigade;
import com.example.techpraktika.service.BrigadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brigades")
public class BrigadeController {

    private final BrigadeService brigadeService;

    public BrigadeController(BrigadeService brigadeService) {this.brigadeService = brigadeService;}

    // Получить все бригады
    @GetMapping
    public List<Brigade> getAllBrigades() {
        return brigadeService.findAll();
    }

    // Получить бригаду по ID
    @GetMapping("/{id}")
    public ResponseEntity<Brigade> getBrigadeById(@PathVariable Long id) {
        Optional<Brigade> brigade = brigadeService.findById(id);
        return brigade.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Создать новую бригаду
    @PostMapping
    public ResponseEntity<Brigade> createBrigade(@RequestBody Brigade brigade) {
        Brigade savedBrigade = brigadeService.save(brigade);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBrigade);
    }

    // Обновить бригаду по ID
    @PutMapping("/{id}")
    public ResponseEntity<Brigade> updateBrigade(@PathVariable Long id, @RequestBody Brigade brigade) {
        Optional<Brigade> existingBrigade = brigadeService.findById(id);
        if (existingBrigade.isPresent()) {
            brigade.setId(id);
            brigadeService.update(brigade);
            return ResponseEntity.ok(brigade);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Удалить бригаду по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrigade(@PathVariable Long id) {
        Optional<Brigade> brigade = brigadeService.findById(id);
        if (brigade.isPresent()) {
            brigadeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
