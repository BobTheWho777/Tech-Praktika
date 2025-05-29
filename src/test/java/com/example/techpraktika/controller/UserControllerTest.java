package com.example.techpraktika.controller;

import com.example.techpraktika.entity.User;
import com.example.techpraktika.impl.UserServiceImpl;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @Mock
    private Model model;

    @InjectMocks
    private UserController controller;

    private User user;
    private List<User> users;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPhone("1234567890");
        users = Arrays.asList(user);
    }

    @Test
    void userList_withoutSearch_returnsAllUsers() {
        when(userService.findAllUser()).thenReturn(users);

        String viewName = controller.userList(null, model);

        assertEquals("admin/user_list", viewName);
        verify(userService).findAllUser();
        verify(model).addAttribute("user_list", users);
    }

    @Test
    void userList_withSearch_returnsFilteredUsers() {
        String search = "test";
        when(userService.findByName(search)).thenReturn(users);

        String viewName = controller.userList(search, model);

        assertEquals("admin/user_list", viewName);
        verify(userService).findByName(search);
        verify(model).addAttribute("user_list", users);
    }

    @Test
    void updateUser_fetchesUserAndReturnsUpdateView() {
        Long id = 1L;
        when(userService.findById(id)).thenReturn(user);

        String viewName = controller.updateUser(id, model);

        assertEquals("admin/user_update", viewName);
        verify(userService).findById(id);
        verify(model).addAttribute("user", user);
    }

    @Test
    void saveUpdate_updatesUserAndRedirects() {
        Long id = 1L;
        String username = "updateduser";
        String email = "updated@example.com";
        String phone = "9876543210";
        String password = "newpassword";
        String role = "ADMIN";

        String viewName = controller.saveUpdate(id, username, email, phone, password, role);

        assertEquals("redirect:/admin/user_list", viewName);
        verify(userService).updateUserFromForm(id, username, email, phone, password, role);
    }

    @Test
    void saveUpdate_withoutPassword_updatesUserAndRedirects() {
        Long id = 1L;
        String username = "updateduser";
        String email = "updated@example.com";
        String phone = "9876543210";
        String role = "ADMIN";

        String viewName = controller.saveUpdate(id, username, email, phone, null, role);

        assertEquals("redirect:/admin/user_list", viewName);
        verify(userService).updateUserFromForm(id, username, email, phone, null, role);
    }

    @Test
    void deleteUser_deletesAndRedirects() {
        Long id = 1L;

        String viewName = controller.deleteUser(id);

        assertEquals("redirect:/admin/user_list", viewName);
        verify(userService).deleteUser(id);
    }
}