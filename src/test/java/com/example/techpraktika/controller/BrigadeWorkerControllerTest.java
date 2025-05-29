package com.example.techpraktika.controller;

import com.example.techpraktika.entity.Brigade;
import com.example.techpraktika.entity.BrigadeWorker;
import com.example.techpraktika.service.BrigadeService;
import com.example.techpraktika.service.BrigadeWorkerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrigadeWorkerControllerTest {

    @Mock
    private BrigadeWorkerService workerService;

    @Mock
    private BrigadeService brigadeService;

    @Mock
    private Model model;

    @InjectMocks
    private BrigadeWorkerController brigadeWorkerController;

    private BrigadeWorker worker;
    private Brigade brigade;
    private List<BrigadeWorker> workerList;

    @BeforeEach
    void setUp() {
        worker = new BrigadeWorker();
        worker.setId(1L);
        worker.setName("Test Worker");

        brigade = new Brigade();
        brigade.setId(1L);
        brigade.setName("Test Brigade");

        workerList = Collections.singletonList(worker);
    }

    @Test
    void listAllWorkers_returnsWorkerListView() {
        when(workerService.findAll()).thenReturn(workerList);
        when(brigadeService.findAll()).thenReturn(Collections.singletonList(brigade));

        String viewName = brigadeWorkerController.listAllWorkers(model);

        assertEquals("brigadeWorkers/list", viewName, "Should return worker list view");
        verify(model).addAttribute("workers", workerList);
        verify(model).addAttribute("brigades", Collections.singletonList(brigade));
    }

    @Test
    void listWorkersByBrigade_brigadeExists_returnsWorkerListView() {
        Long brigadeId = 1L;
        when(brigadeService.findById(brigadeId)).thenReturn(Optional.of(brigade));
        when(workerService.findByBrigadeId(brigadeId)).thenReturn(workerList);

        String viewName = brigadeWorkerController.listWorkersByBrigade(brigadeId, model);

        assertEquals("brigadeWorkers/list", viewName, "Should return worker list view for brigade");
        verify(model).addAttribute("brigade", brigade);
        verify(model).addAttribute("workers", workerList);
        verify(model).addAttribute(eq("newWorker"), any(BrigadeWorker.class));
    }

    @Test
    void listWorkersByBrigade_brigadeNotFound_redirectsToBrigades() {
        Long brigadeId = 1L;
        when(brigadeService.findById(brigadeId)).thenReturn(Optional.empty());

        String viewName = brigadeWorkerController.listWorkersByBrigade(brigadeId, model);

        assertEquals("redirect:/brigades", viewName, "Should redirect to brigades if brigade not found");
        verify(model, never()).addAttribute(anyString(), any());
    }

    @Test
    void createWorker_brigadeExists_savesAndRedirects() {
        Long brigadeId = 1L;
        when(brigadeService.findById(brigadeId)).thenReturn(Optional.of(brigade));

        String viewName = brigadeWorkerController.createWorker(worker, brigadeId);

        assertEquals("redirect:/brigadeWorkers/brigade/" + brigadeId, viewName, "Should redirect to brigade workers list");
        verify(workerService).save(argThat(w -> w.getBrigade() == brigade));
    }

    @Test
    void createWorker_brigadeNotFound_savesAndRedirects() {
        Long brigadeId = 1L;
        when(brigadeService.findById(brigadeId)).thenReturn(Optional.empty());

        String viewName = brigadeWorkerController.createWorker(worker, brigadeId);

        assertEquals("redirect:/brigadeWorkers/brigade/" + brigadeId, viewName, "Should redirect to brigade workers list");
        verify(workerService).save(argThat(w -> w.getBrigade() == null));
    }

    @Test
    void editWorker_brigadeExists_updatesAndRedirects() {
        Long brigadeId = 1L;
        when(brigadeService.findById(brigadeId)).thenReturn(Optional.of(brigade));

        String viewName = brigadeWorkerController.editWorker(worker, brigadeId);

        assertEquals("redirect:/brigadeWorkers/brigade/" + brigadeId, viewName, "Should redirect to brigade workers list");
        verify(workerService).update(argThat(w -> w.getBrigade() == brigade));
    }

    @Test
    void editWorker_brigadeNotFound_updatesAndRedirects() {
        Long brigadeId = 1L;
        when(brigadeService.findById(brigadeId)).thenReturn(Optional.empty());

        String viewName = brigadeWorkerController.editWorker(worker, brigadeId);

        assertEquals("redirect:/brigadeWorkers/brigade/" + brigadeId, viewName, "Should redirect to brigade workers list");
        verify(workerService).update(argThat(w -> w.getBrigade() == null));
    }

    @Test
    void deleteWorker_deletesAndRedirects() {
        Long workerId = 1L;
        Long brigadeId = 1L;

        String viewName = brigadeWorkerController.deleteWorker(workerId, brigadeId);

        assertEquals("redirect:/brigadeWorkers/brigade/" + brigadeId, viewName, "Should redirect to brigade workers list");
        verify(workerService).deleteById(workerId);
    }
}