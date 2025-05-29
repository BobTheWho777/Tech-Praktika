package com.example.techpraktika.controller;

import com.example.techpraktika.entity.ConstructionSite;
import com.example.techpraktika.service.ConstructionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/constructionSites")
public class ConstructionSiteController {

    private final ConstructionService service;

    public ConstructionSiteController(ConstructionService service) {
        this.service = service;
    }

    // Список с возможностью поиска по названию
    @GetMapping
    public String listSites(@RequestParam(required = false) String search, Model model) {
        List<ConstructionSite> allSites = service.findAll();

        List<ConstructionSite> filteredSites = (search == null || search.isBlank())
                ? allSites
                : allSites.stream()
                .filter(site -> site.getName() != null && site.getName().toLowerCase().contains(search.toLowerCase()))
                .collect(Collectors.toList());

        model.addAttribute("constructionSites", filteredSites);
        model.addAttribute("search", search == null ? "" : search);
        return "constructionSite/list";
    }

    //Детали объекта
    @GetMapping("/{id}")
    public String viewSiteDetails(@PathVariable Long id, Model model) {
        var optionalSite = service.findById(id);
        if (optionalSite.isPresent()) {
            ConstructionSite site = optionalSite.get();

            site.getDefects().size();
            site.getBrigades().size();
            site.getSiteStages().size();

            model.addAttribute("constructionSite", site);
            return "constructionSite/details"; // новый шаблон
        } else {
            return "redirect:/constructionSites";
        }
    }


    // Форма создания нового объекта
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("constructionSite", new ConstructionSite());
        return "constructionSite/form";
    }

    // Обработка создания
    @PostMapping
    public String createSite(@ModelAttribute ConstructionSite constructionSite) {
        service.save(constructionSite);
        return "redirect:/constructionSites";
    }

    // Форма редактирования объекта
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        var optionalSite = service.findById(id);
        if (optionalSite.isPresent()) {
            model.addAttribute("constructionSite", optionalSite.get());
            return "constructionSite/form";
        } else {
            return "redirect:/constructionSites";
        }
    }


    @PostMapping("/update/{id}")
    public String updateSite(@PathVariable Long id, @ModelAttribute ConstructionSite constructionSite) {
        constructionSite.setId(id);
        service.update(constructionSite);
        return "redirect:/constructionSites";
    }

    @PostMapping("/delete/{id}")
    public String deleteSite(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/constructionSites";
    }
}
