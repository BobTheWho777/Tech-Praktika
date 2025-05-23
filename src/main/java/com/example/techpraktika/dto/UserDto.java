package com.example.techpraktika.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


public class UserDto {
    private String phone;
    @NotEmpty(message = "Почта не может быть пустой!")
    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", message = "Некорректный адрес электронной почты")
    private String email;
    @NotEmpty(message = "Логин пользователя не может быть пустым!")
    @Pattern(regexp = "^[A-Za-zА-Яа-я0-9_-][A-Za-zА-Яа-я0-9_-]{6,18}$", message = "Логин пользователя может содержать только буквы кириллицы или латиницы, а так же цифры от 0 до 9 и знаки \"-\" и \"_\"")
    @Size(min = 6, max = 18, message = "Логин должен содержать от 6 до 18 символов")
    private String username;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+]).{8,20}$",
            message = "Пароль должен содержать от 8 до 20 символов, включая хотя бы одну букву в верхнем регистре, одну букву в нижнем регистре, одну цифру и один специальный символ (!@#$%^&*()_+)")
    @Size(min = 8, max = 20, message = "Пароль должен быть не короче 8 символов и не длиннее 20")
    private String password;
    private String filename;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
