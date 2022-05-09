package com.security.controllers;

import com.security.entities.User;
import com.security.services.RoleService;
import com.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class  AdminController {

    private RoleService roleService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping()
    public String showUserList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/admin/index";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "/admin/show";
    }

    @GetMapping("/adduser")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.findAll());
        return "/admin/add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@ModelAttribute("user") User user ) {
        userService.save(user);

        return "redirect:/admin";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("allRoles", roleService.findAll());
        return "/admin/update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id,
                         @ModelAttribute("user") User user) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}

