package com.example.techpraktika.controller;

import com.example.techpraktika.entity.Client;
import com.example.techpraktika.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {this.clientService = clientService;}

    //Получить всех клиентов
    @GetMapping
    public List<Client> getAllClients(){
        return clientService.findAll();
    }

    //Получить клиентов по ИД
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id){
        Optional<Client> client = clientService.findById(id);
        return client.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //Создать нового клиента
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        Client savedClient = clientService.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }
    //Обновить клиента по ИД
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client){
        Optional<Client> existingClient = clientService.findById(id);
        if (existingClient.isPresent()){
            client.setId(id);
            clientService.update(client);
            return ResponseEntity.ok(client);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //Удалить клиента
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        Optional<Client> client = clientService.findById(id);
        if (client.isPresent()){
            clientService.deleteById(id);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}