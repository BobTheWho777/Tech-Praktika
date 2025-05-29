package com.example.techpraktika.controller;

import com.example.techpraktika.dto.UserDto;
import com.example.techpraktika.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private UserServiceImpl userServiceImpl;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private AuthController authController;

    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setUsername("testuser");
        userDto.setEmail("test@example.com");
    }

    @Test
    void registrationPage_returnsRegistrationView() {
        String viewName = authController.registrationPage(userDto);

        assertEquals("registration", viewName, "Should return registration view");
    }

    @Test
    void saveUser_success_redirectsToLogin() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userServiceImpl.isUsernameAvailable("testuser")).thenReturn(true);
        when(userServiceImpl.isEmailAvailable("test@example.com")).thenReturn(true);

        String viewName = authController.saveUser(userDto, bindingResult);

        assertEquals("redirect:/login", viewName, "Should redirect to login page");
        verify(userServiceImpl, times(1)).save(userDto);
    }

    @Test
    void saveUser_validationErrors_returnsRegistrationView() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = authController.saveUser(userDto, bindingResult);

        assertEquals("registration", viewName, "Should return registration view on validation errors");
        verify(userServiceImpl, never()).save(any());
    }

    @Test
    void saveUser_usernameTaken_returnsRegistrationView() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userServiceImpl.isUsernameAvailable("testuser")).thenReturn(false);

        String viewName = authController.saveUser(userDto, bindingResult);

        assertEquals("registration", viewName, "Should return registration view when username is taken");
        verify(bindingResult).rejectValue("username", "error.username", "Имя пользователя уже занято!");
        verify(userServiceImpl, never()).save(any());
    }

    @Test
    void saveUser_emailTaken_returnsRegistrationView() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userServiceImpl.isUsernameAvailable("testuser")).thenReturn(true);
        when(userServiceImpl.isEmailAvailable("test@example.com")).thenReturn(false);

        String viewName = authController.saveUser(userDto, bindingResult);

        assertEquals("registration", viewName, "Should return registration view when email is taken");
        verify(bindingResult).rejectValue("email", "error.email", "Почта уже занята!");
        verify(userServiceImpl, never()).save(any());
    }
}