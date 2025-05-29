package com.example.techpraktika.controller;

import com.example.techpraktika.entity.Material;
import com.example.techpraktika.service.MaterialService;
import com.example.techpraktika.service.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/materials")
public class MaterialController {

    private final MaterialService materialService;
    private final SupplierService supplierService;

    public MaterialController(MaterialService materialService, SupplierService supplierService) {
        this.materialService = materialService;
        this.supplierService = supplierService;
    }

    @GetMapping
    public String listMaterials(@RequestParam(required = false) String search, Model model) {
        List<Material> all = materialService.findAll();
        List<Material> filtered = (search == null || search.isBlank())
                ? all
                : all.stream()
                .filter(m -> m.getName().toLowerCase().contains(search.toLowerCase()))
                .toList();
        model.addAttribute("materials", filtered);
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("search", search);
        return "materials/list";
    }

    @PostMapping("/create")
    public String createMaterial(@ModelAttribute Material material) {
        materialService.save(material);
        return "redirect:/materials";
    }

    @PostMapping("/edit")
    public String editMaterial(@ModelAttribute Material material) {
        materialService.update(material);
        return "redirect:/materials";
    }

    @GetMapping("/delete/{id}")
    public String deleteMaterial(@PathVariable Long id) {
        materialService.deleteById(id);
        return "redirect:/materials";
    }
}
