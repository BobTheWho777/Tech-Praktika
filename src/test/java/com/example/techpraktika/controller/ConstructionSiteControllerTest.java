package com.example.techpraktika.controller;

import com.example.techpraktika.entity.ConstructionSite;
import com.example.techpraktika.service.ConstructionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConstructionSiteControllerTest {

    @Mock
    private ConstructionService service;

    @Mock
    private Model model;

    @InjectMocks
    private ConstructionSiteController controller;

    private ConstructionSite site;
    private List<ConstructionSite> siteList;

    @BeforeEach
    void setUp() {
        site = new ConstructionSite();
        site.setId(1L);
        site.setName("Test Site");
        site.setDefects(new HashSet<>());
        site.setBrigades(new ArrayList<>());
        site.setSiteStages(new HashSet<>());

        siteList = Collections.singletonList(site);
    }

    @Test
    void listSites_noSearch_returnsAllSites() {
        when(service.findAll()).thenReturn(siteList);

        String viewName = controller.listSites(null, model);

        assertEquals("constructionSite/list", viewName);
        verify(model).addAttribute("constructionSites", siteList);
        verify(model).addAttribute("search", "");
    }

    @Test
    void listSites_withSearch_returnsFilteredSites() {
        String search = "Test";
        when(service.findAll()).thenReturn(siteList);

        String viewName = controller.listSites(search, model);

        assertEquals("constructionSite/list", viewName);
        verify(model).addAttribute("constructionSites", siteList);
        verify(model).addAttribute("search", search);
    }

    @Test
    void listSites_emptySearch_returnsAllSites() {
        String search = " ";
        when(service.findAll()).thenReturn(siteList);

        String viewName = controller.listSites(search, model);

        assertEquals("constructionSite/list", viewName);
        verify(model).addAttribute("constructionSites", siteList);
        verify(model).addAttribute("search", search);
    }

    @Test
    void viewSiteDetails_siteExists_returnsDetailsView() {
        Long id = 1L;
        when(service.findById(id)).thenReturn(Optional.of(site));

        String viewName = controller.viewSiteDetails(id, model);

        assertEquals("constructionSite/details", viewName);
        verify(model).addAttribute("constructionSite", site);
        verify(service).findById(id);
    }

    @Test
    void viewSiteDetails_siteNotFound_redirectsToList() {
        Long id = 1L;
        when(service.findById(id)).thenReturn(Optional.empty());

        String viewName = controller.viewSiteDetails(id, model);

        assertEquals("redirect:/constructionSites", viewName);
        verify(model, never()).addAttribute(anyString(), any());
    }

    @Test
    void showCreateForm_returnsFormView() {
        String viewName = controller.showCreateForm(model);

        assertEquals("constructionSite/form", viewName);
        verify(model).addAttribute(eq("constructionSite"), any(ConstructionSite.class));
    }

    @Test
    void createSite_savesAndRedirects() {
        String viewName = controller.createSite(site);

        assertEquals("redirect:/constructionSites", viewName);
        verify(service).save(site);
    }

    @Test
    void showEditForm_siteExists_returnsFormView() {
        Long id = 1L;
        when(service.findById(id)).thenReturn(Optional.of(site));

        String viewName = controller.showEditForm(id, model);

        assertEquals("constructionSite/form", viewName);
        verify(model).addAttribute("constructionSite", site);
    }

    @Test
    void showEditForm_siteNotFound_redirectsToList() {
        Long id = 1L;
        when(service.findById(id)).thenReturn(Optional.empty());

        String viewName = controller.showEditForm(id, model);

        assertEquals("redirect:/constructionSites", viewName);
        verify(model, never()).addAttribute(anyString(), any());
    }

    @Test
    void updateSite_updatesAndRedirects() {
        Long id = 1L;

        String viewName = controller.updateSite(id, site);

        assertEquals("redirect:/constructionSites", viewName);
        verify(service).update(argThat(s -> s.getId().equals(id)));
    }

    @Test
    void deleteSite_deletesAndRedirects() {
        Long id = 1L;

        String viewName = controller.deleteSite(id);

        assertEquals("redirect:/constructionSites", viewName);
        verify(service).deleteById(id);
    }
}