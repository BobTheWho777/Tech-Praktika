package com.example.techpraktika.controller;

import com.example.techpraktika.entity.Client;
import com.example.techpraktika.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @Mock
    private Model model;

    @InjectMocks
    private ClientController clientController;

    private Client client;
    private List<Client> clientList;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setId(1L);
        client.setName("Test Client");

        clientList = Collections.singletonList(client);
    }

    @Test
    void listClients_noSearch_returnsAllClients() {
        when(clientService.findAll()).thenReturn(clientList);
        String viewName = clientController.listClients(null, model);

        assertEquals("clients/list", viewName, "Should return clients list view");
        verify(model).addAttribute("clients", clientList);
        verify(model).addAttribute("search", null);
    }

    @Test
    void listClients_withSearch_returnsFilteredClients() {
        String search = "Test";
        when(clientService.findByNameContainingIgnoreCase(search)).thenReturn(clientList);

        String viewName = clientController.listClients(search, model);

        assertEquals("clients/list", viewName, "Should return clients list view with search");
        verify(model).addAttribute("clients", clientList);
        verify(model).addAttribute("search", search);
    }

    @Test
    void listClients_emptySearch_returnsAllClients() {
        String search = " ";
        when(clientService.findAll()).thenReturn(clientList);

        String viewName = clientController.listClients(search, model);

        assertEquals("clients/list", viewName, "Should return clients list view for empty search");
        verify(model).addAttribute("clients", clientList);
        verify(model).addAttribute("search", search);
        verify(clientService).findAll();
        verify(clientService, never()).findByNameContainingIgnoreCase(anyString());
    }

    @Test
    void createClient_savesAndRedirects() {
        String viewName = clientController.createClient(client);

        assertEquals("redirect:/clients", viewName, "Should redirect to clients list");
        verify(clientService).save(client);
    }

    @Test
    void updateClient_updatesAndRedirects() {
        String viewName = clientController.updateClient(client);

        assertEquals("redirect:/clients", viewName, "Should redirect to clients list");
        verify(clientService).update(client);
    }

    @Test
    void deleteClient_deletesAndRedirects() {
        Long clientId = 1L;

        String viewName = clientController.deleteClient(clientId);

        assertEquals("redirect:/clients", viewName, "Should redirect to clients list");
        verify(clientService).deleteById(clientId);
    }
}