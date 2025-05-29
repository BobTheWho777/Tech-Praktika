package com.example.techpraktika.controller;

import com.example.techpraktika.entity.Client;
import com.example.techpraktika.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Список клиентов с поиском по имени
    @GetMapping
    public String listClients(@RequestParam(name = "search", required = false) String search,
                              Model model) {
        List<Client> clients;
        if (search != null && !search.isBlank()) {
            clients = clientService.findByNameContainingIgnoreCase(search);
        } else {
            clients = clientService.findAll();
        }
        model.addAttribute("clients", clients);
        model.addAttribute("search", search);
        return "clients/list";
    }

    // Создать клиента из формы
    @PostMapping("/create")
    public String createClient(@ModelAttribute Client client) {
        clientService.save(client);
        return "redirect:/clients";
    }

    // Редактировать клиента из формы
    @PostMapping("/edit")
    public String updateClient(@ModelAttribute Client client) {
        clientService.update(client);
        return "redirect:/clients";
    }

    // Удалить клиента
    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteById(id);
        return "redirect:/clients";
    }
}

