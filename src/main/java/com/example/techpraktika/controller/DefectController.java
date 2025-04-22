package com.example.techpraktika.controller;

import com.example.techpraktika.entity.Defect;
import com.example.techpraktika.service.DefectService;
import org.atmosphere.config.service.Get;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/defects")
public class DefectController {
    private final DefectService defectService;

    public DefectController(DefectService defectService) {this.defectService = defectService;}

    //Получаем все дефекты
    @GetMapping
    public List<Defect> getAllDefects(){return defectService.findAll();}

    //Получить дефект по ид
    @GetMapping("/{id}")
    public ResponseEntity<Defect> getDefectById (@PathVariable Long id){
        Optional<Defect> defect = defectService.findById(id);
        return defect.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //Создаём новый дефект (Интересно, а штрафы у них большие за дефекты?)
    @PostMapping
    public ResponseEntity<Defect> createDefect(@RequestBody Defect defect){
        Defect savedDefect = defectService.save(defect);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDefect);
    }

    //Обновить дефект по Ид
    @PutMapping("/{id}")
    public ResponseEntity<Defect> updateDefect(@PathVariable Long id,@RequestBody Defect defect){
        Optional<Defect> existingDefect = defectService.findById(id);
        if (existingDefect.isPresent()){
            defect.setId(id);
            defectService.update(defect);
            return ResponseEntity.ok(defect);
        }else { return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
    }

    //Delete Defect by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDefect(@PathVariable Long id){
        Optional<Defect> defect = defectService.findById(id);
        if (defect.isPresent()){
            defectService.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
