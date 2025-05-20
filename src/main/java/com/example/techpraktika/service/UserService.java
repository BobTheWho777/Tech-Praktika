package com.example.techpraktika.service;

import com.example.techpraktika.dto.UserDto;
import com.example.techpraktika.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    void save(UserDto userDto);

    User findByUsername(String username);

    List<User> findAllUser();

    List<User> findByName(String name);

    User findById(Long id);

    void updateUser(User user);

    void deleteUser(Long id);

    boolean isUsernameAvailable(String username);

    boolean isEmailAvailable(String email);
}
