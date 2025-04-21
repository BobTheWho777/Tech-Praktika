package com.example.techpraktika.controller;

import com.example.techpraktika.entity.BrigadeWorker;
import com.example.techpraktika.service.BrigadeWorkerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brigadeWorkers")
public class BrigadeWorkerController {
    private final BrigadeWorkerService brigadeWorkerService;

    public BrigadeWorkerController(BrigadeWorkerService brigadeWorkerService) {this.brigadeWorkerService = brigadeWorkerService;}

    //Получить всех работников
    @GetMapping
    public List<BrigadeWorker> getAllWorker(){
        return brigadeWorkerService.findAll();
    }

    // Получить работника по ID
    @GetMapping("/{id}")
    public ResponseEntity<BrigadeWorker> getWorkerId(@PathVariable Long id){
        Optional<BrigadeWorker> worker = brigadeWorkerService.findById(id);
                return worker.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Создаём нового работника
    @PostMapping
    public ResponseEntity<BrigadeWorker> createWorker(@RequestBody BrigadeWorker worker){
        BrigadeWorker savedWorker = brigadeWorkerService.save(worker);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWorker);
    }

    // Обновляем работника по ИД
    @PutMapping("/{id}")
    public ResponseEntity<BrigadeWorker> updateWorker(@PathVariable Long id, @RequestBody BrigadeWorker worker){
        Optional<BrigadeWorker> existingWorker = brigadeWorkerService.findById(id);
        if(existingWorker.isPresent()){
            worker.setId(id);
            brigadeWorkerService.update(worker);
            return ResponseEntity.ok(worker);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Удаляем работника
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delteWorker(@PathVariable Long id){
        Optional<BrigadeWorker> worker = brigadeWorkerService.findById(id);
        if (worker.isPresent()){
            brigadeWorkerService.deleteById(id);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}