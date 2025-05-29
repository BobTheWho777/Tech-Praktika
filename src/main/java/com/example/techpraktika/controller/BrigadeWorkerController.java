package com.example.techpraktika.controller;

import com.example.techpraktika.entity.BrigadeWorker;
import com.example.techpraktika.service.BrigadeService;
import com.example.techpraktika.service.BrigadeWorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/brigadeWorkers")
public class BrigadeWorkerController {

    private final BrigadeWorkerService workerService;
    private final BrigadeService brigadeService;

    public BrigadeWorkerController(BrigadeWorkerService workerService, BrigadeService brigadeService) {
        this.workerService = workerService;
        this.brigadeService = brigadeService;
    }

    // Показать всех работников из всех бригад
    @GetMapping
    public String listAllWorkers(Model model) {
        model.addAttribute("workers", workerService.findAll());
        model.addAttribute("brigades", brigadeService.findAll());
        return "brigadeWorkers/list";
    }

    // Показать сотрудников конкретной бригады
    @GetMapping("/brigade/{brigadeId}")
    public String listWorkersByBrigade(@PathVariable Long brigadeId, Model model) {
        var brigade = brigadeService.findById(brigadeId);
        if (brigade.isPresent()) {
            model.addAttribute("brigade", brigade.get());
            model.addAttribute("workers", workerService.findByBrigadeId(brigadeId));
            model.addAttribute("newWorker", new BrigadeWorker());
            return "brigadeWorkers/list";
        }
        return "redirect:/brigades";
    }

    // Создать сотрудника
    @PostMapping("/create")
    public String createWorker(@ModelAttribute BrigadeWorker worker, @RequestParam Long brigadeId) {
        brigadeService.findById(brigadeId).ifPresent(worker::setBrigade);
        workerService.save(worker);
        return "redirect:/brigadeWorkers/brigade/" + brigadeId;
    }

    // Обновить сотрудника
    @PostMapping("/edit")
    public String editWorker(@ModelAttribute BrigadeWorker worker, @RequestParam Long brigadeId) {
        brigadeService.findById(brigadeId).ifPresent(worker::setBrigade);
        workerService.update(worker);
        return "redirect:/brigadeWorkers/brigade/" + brigadeId;
    }

    // Удалить сотрудника
    @GetMapping("/delete/{id}")
    public String deleteWorker(@PathVariable Long id, @RequestParam Long brigadeId) {
        workerService.deleteById(id);
        return "redirect:/brigadeWorkers/brigade/" + brigadeId;
    }
}
