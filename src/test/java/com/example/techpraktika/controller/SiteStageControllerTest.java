package com.example.techpraktika.controller;

import com.example.techpraktika.entity.SiteStage;
import com.example.techpraktika.service.ConstructionService;
import com.example.techpraktika.service.SiteStageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SiteStageControllerTest {

    @Mock
    private SiteStageService stageService;

    @Mock
    private ConstructionService constructionSiteService;

    @Mock
    private Model model;

    @InjectMocks
    private SiteStageController controller;

    private SiteStage siteStage;
    private List<SiteStage> siteStages;

    @BeforeEach
    void setUp() {
        siteStage = new SiteStage();
        siteStage.setId(1L);
        siteStage.setName("Test Stage");
        siteStages = Arrays.asList(siteStage);
    }

    @Test
    void listStages_withoutSearch_returnsAllStages() {
        when(stageService.findAll()).thenReturn(siteStages);
        when(constructionSiteService.findAll()).thenReturn(Arrays.asList());

        String viewName = controller.listStages(null, model);

        assertEquals("stages/list", viewName);
        verify(stageService).findAll();
        verify(model).addAttribute("stages", siteStages);
        verify(model).addAttribute("search", null);
        verify(model).addAttribute(eq("constructionSites"), any());
    }

    @Test
    void listStages_withSearch_returnsFilteredStages() {
        String search = "Test";
        when(stageService.findByNameContaining(search)).thenReturn(siteStages);
        when(constructionSiteService.findAll()).thenReturn(Arrays.asList());

        String viewName = controller.listStages(search, model);

        assertEquals("stages/list", viewName);
        verify(stageService).findByNameContaining(search);
        verify(model).addAttribute("stages", siteStages);
        verify(model).addAttribute("search", search);
        verify(model).addAttribute(eq("constructionSites"), any());
    }

    @Test
    void createStage_savesStageAndRedirects() {
        String viewName = controller.createStageFromForm(siteStage);

        assertEquals("redirect:/stages", viewName);
        verify(stageService).save(siteStage);
    }

    @Test
    void updateStage_existingStage_updatesAndRedirects() {
        when(stageService.findById(1L)).thenReturn(Optional.of(siteStage));

        String viewName = controller.updateStageFromForm(siteStage);

        assertEquals("redirect:/stages", viewName);
        verify(stageService).findById(1L);
        verify(stageService).update(siteStage);
    }

    @Test
    void updateStage_nonExistingStage_doesNotUpdateAndRedirects() {
        when(stageService.findById(1L)).thenReturn(Optional.empty());

        String viewName = controller.updateStageFromForm(siteStage);

        assertEquals("redirect:/stages", viewName);
        verify(stageService).findById(1L);
        verify(stageService, never()).update(any());
    }

    @Test
    void deleteStage_deletesAndRedirects() {
        Long id = 1L;

        String viewName = controller.deleteStage(id);

        assertEquals("redirect:/stages", viewName);
        verify(stageService).deleteById(id);
    }
}