package com.example.techpraktika.controller;

import com.example.techpraktika.entity.Brigade;
import com.example.techpraktika.service.BrigadeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/brigades")
public class BrigadeController {

    private final BrigadeService brigadeService;

    public BrigadeController(BrigadeService brigadeService) {
        this.brigadeService = brigadeService;
    }

    // Показать страницу со списком бригад
    @GetMapping
    public String listBrigades(@RequestParam(name = "search", required = false) String search,
                               Model model) {
        List<Brigade> brigades;
        if (search != null && !search.isEmpty()) {
            brigades = brigadeService.findByNameContaining(search);
        } else {
            brigades = brigadeService.findAll();
        }
        model.addAttribute("brigades", brigades);
        model.addAttribute("search", search);
        return "brigades/list";
    }

    // Создать бригаду из формы
    @PostMapping("/create")
    public String createBrigadeFromForm(@ModelAttribute Brigade brigade) {
        brigadeService.save(brigade);
        return "redirect:/brigades";
    }

    // Редактировать бригаду из формы
    @PostMapping("/edit")
    public String updateBrigadeFromForm(@ModelAttribute Brigade brigade) {
        Optional<Brigade> existing = brigadeService.findById(brigade.getId());
        if (existing.isPresent()) {
            brigadeService.update(brigade);
        }
        return "redirect:/brigades";
    }

    // Удалить бригаду
    @GetMapping("/delete/{id}")
    public String deleteBrigade(@PathVariable Long id) {
        brigadeService.deleteById(id);
        return "redirect:/brigades";
    }
}
