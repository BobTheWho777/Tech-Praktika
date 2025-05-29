package com.example.techpraktika.controller;

import com.example.techpraktika.entity.SiteStage;
import com.example.techpraktika.service.ConstructionService;
import com.example.techpraktika.service.SiteStageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/stages")
public class SiteStageController {

    private final SiteStageService stageService;
    private final ConstructionService constructionSiteService;

    public SiteStageController(SiteStageService stageService, ConstructionService constructionSiteService) {
        this.stageService = stageService;
        this.constructionSiteService = constructionSiteService;
    }

    @GetMapping
    public String listStages(@RequestParam(name = "search", required = false) String search,
                             Model model) {
        List<SiteStage> stages;
        if (search != null && !search.isEmpty()) {
            stages = stageService.findByNameContaining(search);
        } else {
            stages = stageService.findAll();
        }
        model.addAttribute("stages", stages);
        model.addAttribute("search", search);
        model.addAttribute("constructionSites", constructionSiteService.findAll());
        return "stages/list";
    }

    @PostMapping("/create")
    public String createStageFromForm(@ModelAttribute SiteStage stage) {
        stageService.save(stage);
        return "redirect:/stages";
    }

    @PostMapping("/edit")
    public String updateStageFromForm(@ModelAttribute SiteStage stage) {
        Optional<SiteStage> existing = stageService.findById(stage.getId());
        if (existing.isPresent()) {
            stageService.update(stage);
        }
        return "redirect:/stages";
    }

    @GetMapping("/delete/{id}")
    public String deleteStage(@PathVariable Long id) {
        stageService.deleteById(id);
        return "redirect:/stages";
    }
}
