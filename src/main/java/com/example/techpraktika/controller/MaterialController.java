package com.example.techpraktika.controller;

import com.example.techpraktika.entity.Material;
import com.example.techpraktika.service.MaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    private final MaterialService service;

    public MaterialController(MaterialService service) {this.service = service;}

    //Получить все материалы
    @GetMapping
    public List<Material> getAllMat(){return service.findAll();}

    //Получить материалы по ИД
    @GetMapping("/{id}")
    public ResponseEntity<Material> getMatById(@PathVariable Long id){
        Optional<Material> material = service.findById(id);
        return material.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //Создать новый материал
    @PostMapping
    public ResponseEntity<Material> createMat(@RequestBody Material material){
        Material savedMat = service.save(material);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMat);
    }
    //Обновить материал по ИД
    @PutMapping("/{id}")
    public ResponseEntity<Material> updateMat(@PathVariable Long id, @RequestBody Material material){
        Optional<Material> existingMat = service.findById(id);
        if (existingMat.isPresent()){
            material.setId(id);
            service.update(material);
            return ResponseEntity.ok(material);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    //Удалить материал по ИД
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMat(@PathVariable Long id){
        Optional<Material> material = service.findById(id);
        if(material.isPresent()){
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}