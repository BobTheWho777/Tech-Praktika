package com.example.techpraktika.controller;

import com.example.techpraktika.entity.ConstructionSite;
import com.example.techpraktika.service.ConstructionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/constructionSites")
public class ConstructionSiteController {
    private final ConstructionService service;

    public ConstructionSiteController(ConstructionService service) {
        this.service = service;
    }

    //Получить все объекты
    @GetMapping
    public List<ConstructionSite> getAllConstSite(){
        return  service.findAll();
    }

    //Получить объект по Ид
    @GetMapping("/{id}")
    public ResponseEntity<ConstructionSite> getSiteId(@PathVariable Long id){
        Optional<ConstructionSite> site = service.findById(id);
        return site.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //Создаём новый объект
    @PostMapping
    public ResponseEntity<ConstructionSite> createSite(@RequestBody ConstructionSite site){
        ConstructionSite savedSite = service.save(site);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSite);
    }

    //Обновляем объект по ИД
    @PutMapping("/{id}")
    public ResponseEntity<ConstructionSite> updateSite (@PathVariable Long id, @RequestBody ConstructionSite site){
        Optional<ConstructionSite> existingSite = service.findById(id);
        if (existingSite.isPresent()){
            site.setId(id);
            service.update(site);
            return ResponseEntity.ok(site);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //Удаляем объект
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSite(@PathVariable Long id){
        Optional<ConstructionSite> site = service.findById(id);
        if (site.isPresent()){
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
