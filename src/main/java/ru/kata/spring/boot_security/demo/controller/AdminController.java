package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UsersService;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UsersService usersService;
    private final RoleService roleService;

    public AdminController(UsersService usersService, RoleService roleService) {
        this.usersService = usersService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showAllUsers(ModelMap model) {
        model.addAttribute("person", usersService.getAllUsers());
        return "allUsers";
    }

    @GetMapping("/{id}")
    public String showUsers(@PathVariable("id") long id, Model model) {
        model.addAttribute("person", usersService.getUserById(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("person") User user) {
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid User user,
                         BindingResult bindingResult, @RequestParam("listRoles") ArrayList<Long> roles) {
        if (bindingResult.hasErrors())
            return "new";

        usersService.saveNewUser(user, roleService.findRoles(roles));
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("person", usersService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid User user,
                         BindingResult bindingResult, @RequestParam("listRoles") ArrayList<Long> roles) {
        if (bindingResult.hasErrors())
            return "edit";

        usersService.updateUser(user, roleService.findRoles(roles));
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        usersService.deleteUserById(id);
        return "redirect:/admin";
    }
}