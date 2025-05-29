package com.example.techpraktika.impl;

import com.example.techpraktika.dto.UserDto;
import com.example.techpraktika.entity.Role;
import com.example.techpraktika.entity.User;
import com.example.techpraktika.repository.UserRepo;
import com.example.techpraktika.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> findAllUser() {
        return userRepo.findAll();
    }

    @Override
    public void save(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
    }

    @Override
    public List<User> findByName(String name) {
        return userRepo.findByName(name);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Пользователь с ID: " + id + " не найден!"));
    }

    @Override
    public void updateUser(User user) {
        User updateUser = userRepo.findById(user.getId()).orElse(null);
        if (updateUser == null) {
            throw new IllegalArgumentException("Пользователь не найден!");
        }
        updateUser.setUsername(user.getUsername());
        updateUser.setPhone(user.getPhone());
        userRepo.save(updateUser);
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepo.deleteById(id);
    }


    @Override
    public boolean isUsernameAvailable(String username) {
        return !userRepo.existsByUsername(username);
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return userRepo.existsByEmail(email);
    }

    public void updateUserFromForm(Long id, String username, String email, String phone, String password, String roleStr) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Пользователь не найден!"));

        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);

        if (password != null && !password.isBlank()) {
            user.setPassword(passwordEncoder.encode(password));
        }

        user.setRoles(Collections.singleton(Role.valueOf(roleStr)));
        userRepo.save(user);
    }
}
