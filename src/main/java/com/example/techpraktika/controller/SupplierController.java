package com.example.techpraktika.controller;

import com.example.techpraktika.entity.Supplier;
import com.example.techpraktika.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping
    public String listSuppliers(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(defaultValue = "name") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model
    ) {
        List<Supplier> suppliers = supplierService.findAll();

        if (search != null && !search.isBlank()) {
            String lowered = search.toLowerCase();
            suppliers = suppliers.stream()
                    .filter(s ->
                            s.getName().toLowerCase().contains(lowered) ||
                                    (s.getContact() != null && s.getContact().toLowerCase().contains(lowered)) ||
                                    (s.getPhone()   != null && s.getPhone().toLowerCase().contains(lowered)) ||
                                    (s.getEmail()   != null && s.getEmail().toLowerCase().contains(lowered))
                    )
                    .collect(Collectors.toList());
        }

        suppliers = suppliers.stream()
                .sorted((s1, s2) -> {
                    int cmp;
                    switch (sortField) {
                        case "contact" -> cmp = s1.getContact().compareToIgnoreCase(s2.getContact());
                        case "phone"   -> cmp = s1.getPhone().compareToIgnoreCase(s2.getPhone());
                        case "email"   -> cmp = s1.getEmail().compareToIgnoreCase(s2.getEmail());
                        default        -> cmp = s1.getName().compareToIgnoreCase(s2.getName());
                    }
                    return "asc".equals(sortDir) ? cmp : -cmp;
                })
                .toList();

        model.addAttribute("suppliers", suppliers);
        model.addAttribute("search", search);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", "asc".equals(sortDir) ? "desc" : "asc");
        return "supplier/list";
    }

    @PostMapping("/create")
    public String addSupplier(@ModelAttribute Supplier supplier) {
        supplierService.save(supplier);
        return "redirect:/suppliers";
    }

    @PostMapping("/edit")
    public String updateSupplier(@ModelAttribute Supplier supplier) {
        supplierService.update(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable Long id) {
        supplierService.deleteById(id);
        return "redirect:/suppliers";
    }
}
