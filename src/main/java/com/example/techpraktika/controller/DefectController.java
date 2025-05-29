package com.example.techpraktika.controller;

import com.example.techpraktika.entity.ConstructionSite;
import com.example.techpraktika.entity.Defect;
import com.example.techpraktika.service.ConstructionService;
import com.example.techpraktika.service.DefectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/defects")
public class DefectController {

    private final DefectService defectService;
    private final ConstructionService constructionSiteService;

    public DefectController(DefectService defectService, ConstructionService constructionSiteService) {
        this.defectService = defectService;
        this.constructionSiteService = constructionSiteService;
    }

    @GetMapping
    public String listDefects(@RequestParam(name = "search", required = false) String search,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "10") int size,
                              Model model) {
        Page<Defect> defectPage;
        Pageable pageable = PageRequest.of(page, size);
        if (search != null && !search.isEmpty()) {
            defectPage = defectService.findByDescriptionContaining(search, pageable);
        } else {
            defectPage = defectService.findAll(pageable);
        }
        model.addAttribute("defects", defectPage.getContent());
        model.addAttribute("search", search);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", defectPage.getTotalPages());
        model.addAttribute("constructionSites", constructionSiteService.findAll());
        return "defects/list";
    }

    @PostMapping("/create")
    public String createDefectFromForm(@ModelAttribute Defect defect, @RequestParam(name = "constructionSiteId", required = false) Long constructionSiteId) {
        if (constructionSiteId != null) {
            Optional<ConstructionSite> site = constructionSiteService.findById(constructionSiteId);
            site.ifPresent(defect::setConstructionSite);
        }
        defectService.save(defect);
        return "redirect:/defects";
    }

    @PostMapping("/edit")
    public String updateDefectFromForm(@ModelAttribute Defect defect, @RequestParam(name = "constructionSiteId", required = false) Long constructionSiteId) {
        Optional<Defect> existing = defectService.findById(defect.getId());
        if (existing.isPresent()) {
            if (constructionSiteId != null) {
                Optional<ConstructionSite> site = constructionSiteService.findById(constructionSiteId);
                site.ifPresent(defect::setConstructionSite);
            } else {
                defect.setConstructionSite(null);
            }
            defectService.update(defect);
        }
        return "redirect:/defects";
    }

    @GetMapping("/delete/{id}")
    public String deleteDefect(@PathVariable Long id) {
        defectService.deleteById(id);
        return "redirect:/defects";
    }
}
