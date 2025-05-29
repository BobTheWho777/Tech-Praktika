package com.example.techpraktika.controller;

import com.example.techpraktika.entity.Brigade;
import com.example.techpraktika.service.BrigadeService;
import com.example.techpraktika.service.BrigadeWorkerService;
import com.example.techpraktika.service.ConstructionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/brigades")
public class BrigadeController {

    private final BrigadeService brigadeService;
    private final BrigadeWorkerService workerService;
    private final ConstructionService constructionService;

    public BrigadeController(BrigadeService brigadeService, BrigadeWorkerService workerService, ConstructionService constructionService) {
        this.brigadeService = brigadeService;
        this.workerService = workerService;
        this.constructionService = constructionService;
    }

    // Показать страницу со списком бригад
    @GetMapping
    public String listBrigades(@RequestParam(name = "search", required = false) String search, Model model) {
        List<Brigade> brigades;
        if (search != null && !search.isEmpty()) {
            brigades = brigadeService.findByNameContaining(search);
        } else {
            brigades = brigadeService.findAll();
        }
        model.addAttribute("brigades", brigades);
        model.addAttribute("search", search);
        model.addAttribute("workerService", workerService);
        model.addAttribute("allConstructionSites", constructionService.findAll());
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

    // Добавить строительный объект к бригаде
    @PostMapping("/addSite")
    public String addConstructionSite(@RequestParam Long brigadeId, @RequestParam Long siteId) {
        brigadeService.addConstructionSite(brigadeId, siteId);
        return "redirect:/brigades";
    }

    // Удалить строительный объект из бригады
    @PostMapping("/removeSite/{brigadeId}/{siteId}")
    public String removeConstructionSite(@PathVariable Long brigadeId, @PathVariable Long siteId) {
        brigadeService.removeConstructionSite(brigadeId, siteId);
        return "redirect:/brigades";
    }
}