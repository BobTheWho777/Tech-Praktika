package com.example.techpraktika.controller;

import com.example.techpraktika.entity.Brigade;
import com.example.techpraktika.service.BrigadeService;
import com.example.techpraktika.service.BrigadeWorkerService;
import com.example.techpraktika.service.ConstructionService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrigadeControllerTest {

    @Mock
    private BrigadeService brigadeService;

    @Mock
    private BrigadeWorkerService workerService;

    @Mock
    private ConstructionService constructionService;

    @Mock
    private Model model;

    @InjectMocks
    private BrigadeController brigadeController;

    private Brigade brigade;
    private List<Brigade> brigadeList;

    @BeforeEach
    void setUp() {
        brigade = new Brigade();
        brigade.setId(1L);
        brigade.setName("Test Brigade");
        brigadeList = Collections.singletonList(brigade);
    }

    @Test
    void listBrigades_noSearch_returnsAllBrigades() {
        when(brigadeService.findAll()).thenReturn(brigadeList);
        when(constructionService.findAll()).thenReturn(Collections.emptyList());

        String viewName = brigadeController.listBrigades(null, model);

        assertEquals("brigades/list", viewName, "Should return brigades list view");
        verify(model).addAttribute("brigades", brigadeList);
        verify(model).addAttribute("search", null);
        verify(model).addAttribute("workerService", workerService);
        verify(model).addAttribute("allConstructionSites", Collections.emptyList());
    }

    @Test
    void listBrigades_withSearch_returnsFilteredBrigades() {
        String search = "Test";
        when(brigadeService.findByNameContaining(search)).thenReturn(brigadeList);
        when(constructionService.findAll()).thenReturn(Collections.emptyList());

        String viewName = brigadeController.listBrigades(search, model);

        assertEquals("brigades/list", viewName, "Should return brigades list view with search");
        verify(model).addAttribute("brigades", brigadeList);
        verify(model).addAttribute("search", search);
        verify(model).addAttribute("workerService", workerService);
        verify(model).addAttribute("allConstructionSites", Collections.emptyList());
    }

    @Test
    void createBrigadeFromForm_savesAndRedirects() {
        String viewName = brigadeController.createBrigadeFromForm(brigade);

        assertEquals("redirect:/brigades", viewName, "Should redirect to brigades list");
        verify(brigadeService).save(brigade);
    }

    @Test
    void updateBrigadeFromForm_existingBrigade_updatesAndRedirects() {
        when(brigadeService.findById(brigade.getId())).thenReturn(Optional.of(brigade));

        String viewName = brigadeController.updateBrigadeFromForm(brigade);

        assertEquals("redirect:/brigades", viewName, "Should redirect to brigades list");
        verify(brigadeService).update(brigade);
    }

    @Test
    void updateBrigadeFromForm_nonExistingBrigade_redirects() {
        when(brigadeService.findById(brigade.getId())).thenReturn(Optional.empty());

        String viewName = brigadeController.updateBrigadeFromForm(brigade);

        assertEquals("redirect:/brigades", viewName, "Should redirect to brigades list");
        verify(brigadeService, never()).update(any());
    }

    @Test
    void deleteBrigade_deletesAndRedirects() {
        Long brigadeId = 1L;

        String viewName = brigadeController.deleteBrigade(brigadeId);

        assertEquals("redirect:/brigades", viewName, "Should redirect to brigades list");
        verify(brigadeService).deleteById(brigadeId);
    }

    @Test
    void addConstructionSite_addsAndRedirects() {
        Long brigadeId = 1L;
        Long siteId = 2L;

        String viewName = brigadeController.addConstructionSite(brigadeId, siteId);

        assertEquals("redirect:/brigades", viewName, "Should redirect to brigades list");
        verify(brigadeService).addConstructionSite(brigadeId, siteId);
    }

    @Test
    void removeConstructionSite_removesAndRedirects() {
        Long brigadeId = 1L;
        Long siteId = 2L;

        String viewName = brigadeController.removeConstructionSite(brigadeId, siteId);

        assertEquals("redirect:/brigades", viewName, "Should redirect to brigades list");
        verify(brigadeService).removeConstructionSite(brigadeId, siteId);
    }
}