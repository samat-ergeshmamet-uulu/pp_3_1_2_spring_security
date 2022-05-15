package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.UsersService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String showUsers(Model model, Principal principal) {
        model.addAttribute("person", usersService.getUserName(principal.getName()));
        return "user";
    }
}
