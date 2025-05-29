package com.example.techpraktika.controller;

import com.example.techpraktika.entity.Material;
import com.example.techpraktika.entity.Supplier;
import com.example.techpraktika.service.MaterialService;
import com.example.techpraktika.service.SupplierService;
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
class MaterialControllerTest {

    @Mock
    private MaterialService materialService;

    @Mock
    private SupplierService supplierService;

    @Mock
    private Model model;

    @InjectMocks
    private MaterialController controller;

    private Material material;
    private Supplier supplier;
    private List<Material> materialList;
    private List<Supplier> supplierList;

    @BeforeEach
    void setUp() {
        material = new Material();
        material.setId(1L);
        material.setName("Test Material");

        supplier = new Supplier();
        supplier.setId(1L);
        supplier.setName("Test Supplier");

        materialList = Collections.singletonList(material);
        supplierList = Collections.singletonList(supplier);
    }

    @Test
    void listMaterials_noSearch_returnsAllMaterials() {
        when(materialService.findAll()).thenReturn(materialList);
        when(supplierService.findAll()).thenReturn(supplierList);

        String viewName = controller.listMaterials(null, model);

        assertEquals("materials/list", viewName);
        verify(model).addAttribute("materials", materialList);
        verify(model).addAttribute("suppliers", supplierList);
        verify(model).addAttribute("search", null);
    }

    @Test
    void listMaterials_withSearch_returnsFilteredMaterials() {
        String search = "Test";
        when(materialService.findAll()).thenReturn(materialList);
        when(supplierService.findAll()).thenReturn(supplierList);

        String viewName = controller.listMaterials(search, model);

        assertEquals("materials/list", viewName);
        verify(model).addAttribute("materials", materialList);
        verify(model).addAttribute("suppliers", supplierList);
        verify(model).addAttribute("search", search);
    }

    @Test
    void listMaterials_emptySearch_returnsAllMaterials() {
        String search = " ";
        when(materialService.findAll()).thenReturn(materialList);
        when(supplierService.findAll()).thenReturn(supplierList);

        String viewName = controller.listMaterials(search, model);

        assertEquals("materials/list", viewName);
        verify(model).addAttribute("materials", materialList);
        verify(model).addAttribute("suppliers", supplierList);
        verify(model).addAttribute("search", search);
    }

    @Test
    void createMaterial_savesAndRedirects() {
        String viewName = controller.createMaterial(material);

        assertEquals("redirect:/materials", viewName);
        verify(materialService).save(material);
    }

    @Test
    void editMaterial_updatesAndRedirects() {
        String viewName = controller.editMaterial(material);

        assertEquals("redirect:/materials", viewName);
        verify(materialService).update(material);
    }

    @Test
    void deleteMaterial_deletesAndRedirects() {
        Long id = 1L;

        String viewName = controller.deleteMaterial(id);

        assertEquals("redirect:/materials", viewName);
        verify(materialService).deleteById(id);
    }
}