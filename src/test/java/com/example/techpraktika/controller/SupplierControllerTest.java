package com.example.techpraktika.controller;

import com.example.techpraktika.entity.Supplier;
import com.example.techpraktika.service.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SupplierControllerTest {

    @Mock
    private SupplierService supplierService;

    @Mock
    private Model model;

    @InjectMocks
    private SupplierController controller;

    private Supplier supplier;
    private List<Supplier> suppliers;

    @BeforeEach
    void setUp() {
        supplier = new Supplier();
        supplier.setId(1L);
        supplier.setName("Test Supplier");
        supplier.setContact("John Doe");
        supplier.setPhone("1234567890");
        supplier.setEmail("test@example.com");
        suppliers = Arrays.asList(supplier);
    }

    @Test
    void listSuppliers_withoutSearchAndDefaultSort_returnsAllSuppliers() {
        when(supplierService.findAll()).thenReturn(suppliers);

        String viewName = controller.listSuppliers(null, "name", "asc", model);

        assertEquals("supplier/list", viewName);
        verify(supplierService).findAll();
        verify(model).addAttribute("suppliers", suppliers);
        verify(model).addAttribute("search", null);
        verify(model).addAttribute("sortField", "name");
        verify(model).addAttribute("sortDir", "asc");
        verify(model).addAttribute("reverseSortDir", "desc");
    }

    @Test
    void listSuppliers_withSearch_filtersSuppliers() {
        String search = "test";
        when(supplierService.findAll()).thenReturn(suppliers);

        String viewName = controller.listSuppliers(search, "name", "asc", model);

        assertEquals("supplier/list", viewName);
        verify(supplierService).findAll();
        verify(model).addAttribute(eq("suppliers"), any());
        verify(model).addAttribute("search", search);
        verify(model).addAttribute("sortField", "name");
        verify(model).addAttribute("sortDir", "asc");
        verify(model).addAttribute("reverseSortDir", "desc");
    }

    @Test
    void listSuppliers_withSortByContactDesc_sortsCorrectly() {
        when(supplierService.findAll()).thenReturn(suppliers);

        String viewName = controller.listSuppliers(null, "contact", "desc", model);

        assertEquals("supplier/list", viewName);
        verify(supplierService).findAll();
        verify(model).addAttribute(eq("suppliers"), any());
        verify(model).addAttribute("search", null);
        verify(model).addAttribute("sortField", "contact");
        verify(model).addAttribute("sortDir", "desc");
        verify(model).addAttribute("reverseSortDir", "asc");
    }

    @Test
    void addSupplier_savesSupplierAndRedirects() {
        String viewName = controller.addSupplier(supplier);

        assertEquals("redirect:/suppliers", viewName);
        verify(supplierService).save(supplier);
    }

    @Test
    void updateSupplier_updatesSupplierAndRedirects() {
        String viewName = controller.updateSupplier(supplier);

        assertEquals("redirect:/suppliers", viewName);
        verify(supplierService).update(supplier);
    }

    @Test
    void deleteSupplier_deletesAndRedirects() {
        Long id = 1L;

        String viewName = controller.deleteSupplier(id);

        assertEquals("redirect:/suppliers", viewName);
        verify(supplierService).deleteById(id);
    }
}