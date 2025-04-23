package com.example.techpraktika.controller;

import com.example.techpraktika.entity.Brigade;
import com.example.techpraktika.entity.Supplier;
import com.example.techpraktika.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    private final SupplierService service;

    public SupplierController(SupplierService service) {this.service = service;}

    //Получить всех поставщиков
    @GetMapping
    public List<Supplier> getAllSuplier(){ return service.findAll();}
    //Получить поставщика по ИД
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupllierById(@PathVariable Long id){
        Optional<Supplier> supplier = service.findById(id);
        return supplier.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    //Создать нового поставщика
    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        Supplier savedSupplier = service.save(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSupplier);
    }
    //Обновить поставщика по ИД
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
        Optional<Supplier> existingSupplier = service.findById(id);
        if (existingSupplier.isPresent()) {
            supplier.setId(id);
            service.update(supplier);
            return ResponseEntity.ok(supplier);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    //Удалить поставщика по ИД
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        Optional<Supplier> supplier = service.findById(id);
        if (supplier.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
