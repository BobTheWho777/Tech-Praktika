package com.example.techpraktika.controller;

import com.example.techpraktika.entity.ConstructionSite;
import com.example.techpraktika.entity.Defect;
import com.example.techpraktika.service.ConstructionService;
import com.example.techpraktika.service.DefectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefectControllerTest {

    @Mock
    private DefectService defectService;

    @Mock
    private ConstructionService constructionSiteService;

    @Mock
    private Model model;

    @InjectMocks
    private DefectController controller;

    private Defect defect;
    private ConstructionSite site;
    private List<Defect> defectList;
    private Page<Defect> defectPage;

    @BeforeEach
    void setUp() {
        defect = new Defect();
        defect.setId(1L);
        defect.setDescription("Test Defect");

        site = new ConstructionSite();
        site.setId(1L);
        site.setName("Test Site");
        site.setDefects(new HashSet<>());
        site.setBrigades(new ArrayList<>());
        site.setSiteStages(new HashSet<>());

        defectList = Collections.singletonList(defect);
        defectPage = new PageImpl<>(defectList, PageRequest.of(0, 10), 1);
    }

    @Test
    void listDefects_noSearch_returnsAllDefects() {
        Pageable pageable = PageRequest.of(0, 10);
        when(defectService.findAll(eq(pageable))).thenReturn(defectPage);
        when(constructionSiteService.findAll()).thenReturn(Collections.singletonList(site));

        String viewName = controller.listDefects(null, 0, 10, model);

        assertEquals("defects/list", viewName);
        verify(model).addAttribute("defects", defectPage.getContent());
        verify(model).addAttribute("search", null);
        verify(model).addAttribute("page", 0);
        verify(model).addAttribute("totalPages", defectPage.getTotalPages());
        verify(model).addAttribute("constructionSites", Collections.singletonList(site));
    }

    @Test
    void listDefects_withSearch_returnsFilteredDefects() {
        String search = "Test";
        Pageable pageable = PageRequest.of(0, 10);
        when(defectService.findByDescriptionContaining(eq(search), eq(pageable))).thenReturn(defectPage);
        when(constructionSiteService.findAll()).thenReturn(Collections.singletonList(site));

        String viewName = controller.listDefects(search, 0, 10, model);

        assertEquals("defects/list", viewName);
        verify(model).addAttribute("defects", defectPage.getContent());
        verify(model).addAttribute("search", search);
        verify(model).addAttribute("page", 0);
        verify(model).addAttribute("totalPages", defectPage.getTotalPages());
        verify(model).addAttribute("constructionSites", Collections.singletonList(site));
    }

    @Test
    void listDefects_emptySearch_returnsAllDefects() {
        String search = " ";
        Pageable pageable = PageRequest.of(0, 10);
        when(defectService.findByDescriptionContaining(eq(search), eq(pageable))).thenReturn(defectPage);
        when(constructionSiteService.findAll()).thenReturn(Collections.singletonList(site));

        String viewName = controller.listDefects(search, 0, 10, model);

        assertEquals("defects/list", viewName);
        verify(model).addAttribute("defects", defectPage.getContent());
        verify(model).addAttribute("search", search);
        verify(model).addAttribute("page", 0);
        verify(model).addAttribute("totalPages", defectPage.getTotalPages());
        verify(model).addAttribute("constructionSites", Collections.singletonList(site));
    }

    @Test
    void createDefectFromForm_withConstructionSite_savesAndRedirects() {
        Long constructionSiteId = 1L;
        when(constructionSiteService.findById(constructionSiteId)).thenReturn(Optional.of(site));

        String viewName = controller.createDefectFromForm(defect, constructionSiteId);

        assertEquals("redirect:/defects", viewName);
        verify(defectService).save(argThat(d -> d.getConstructionSite() == site));
    }

    @Test
    void createDefectFromForm_noConstructionSite_savesAndRedirects() {
        String viewName = controller.createDefectFromForm(defect, null);

        assertEquals("redirect:/defects", viewName);
        verify(defectService).save(argThat(d -> d.getConstructionSite() == null));
    }

    @Test
    void updateDefectFromForm_existingDefectWithConstructionSite_updatesAndRedirects() {
        Long constructionSiteId = 1L;
        when(defectService.findById(defect.getId())).thenReturn(Optional.of(defect));
        when(constructionSiteService.findById(constructionSiteId)).thenReturn(Optional.of(site));

        String viewName = controller.updateDefectFromForm(defect, constructionSiteId);

        assertEquals("redirect:/defects", viewName);
        verify(defectService).update(argThat(d -> d.getConstructionSite() == site));
    }

    @Test
    void updateDefectFromForm_existingDefectNoConstructionSite_updatesAndRedirects() {
        when(defectService.findById(defect.getId())).thenReturn(Optional.of(defect));

        String viewName = controller.updateDefectFromForm(defect, null);

        assertEquals("redirect:/defects", viewName);
        verify(defectService).update(argThat(d -> d.getConstructionSite() == null));
    }

    @Test
    void updateDefectFromForm_nonExistingDefect_redirects() {
        when(defectService.findById(defect.getId())).thenReturn(Optional.empty());

        String viewName = controller.updateDefectFromForm(defect, null);

        assertEquals("redirect:/defects", viewName);
        verify(defectService, never()).update(any());
    }

    @Test
    void deleteDefect_deletesAndRedirects() {
        Long id = 1L;

        String viewName = controller.deleteDefect(id);

        assertEquals("redirect:/defects", viewName);
        verify(defectService).deleteById(id);
    }
}