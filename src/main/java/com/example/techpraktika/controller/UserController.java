package com.example.techpraktika.controller;

import com.example.techpraktika.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;


    @GetMapping("/list")
    String userList(@RequestParam(value = "search", required = false) String search,
                    Model model) {
        model.addAttribute("user_list", search == null || search.isEmpty()
                ? userService.findAllUser()
                : userService.findByName(search));
        return "admin/user_list";
    }

    @GetMapping("/update")
    String updateUser(@RequestParam(value = "id",required = false) Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "admin/user_update";
    }

    @PostMapping("/update")
    String saveUpdate(@RequestParam Long id,
                      @RequestParam String username,
                      @RequestParam String email,
                      @RequestParam String phone,
                      @RequestParam(required = false) String password,
                      @RequestParam String role) {

        userService.updateUserFromForm(id, username, email, phone, password, role);
        return "redirect:/admin/user_list";
    }


    @GetMapping("/delete")
    String deleteUser(@RequestParam(value = "id",required = false) Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/user_list";
    }
}
