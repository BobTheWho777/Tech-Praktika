package com.example.techpraktika.controller;

import com.example.techpraktika.entity.SiteStage;
import com.example.techpraktika.service.SiteStageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stages")
public class SiteStageController {
    private final SiteStageService service;

    public SiteStageController(SiteStageService service) {this.service = service;}

    //Получить все стадии
    @GetMapping
    public List<SiteStage> getAllStage(){return service.findAll();}
    //Получить стадию по ИД
    @GetMapping("/{id}")
    public ResponseEntity<SiteStage> getStageById(@PathVariable Long id){
        Optional<SiteStage> stage = service.findById(id);
        return stage.map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    //Создать новую стадию
    @PostMapping
    public ResponseEntity<SiteStage> createStage(@RequestBody SiteStage siteStage){
        SiteStage savedStage = service.save(siteStage);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStage);
    }
    //Обновить стадию по ИД
    @PutMapping("/{id}")
    public ResponseEntity<SiteStage> updateStage(@PathVariable Long id, @RequestBody SiteStage stage){
        Optional<SiteStage> existingStage = service.findById(id);
        if (existingStage.isPresent()){
            stage.setId(id);
            service.update(stage);
            return  ResponseEntity.ok(stage);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    //Удалить стадию по ИД
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStage(@PathVariable Long id){
        Optional<SiteStage> stage = service.findById(id);
        if(stage.isPresent()){
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
