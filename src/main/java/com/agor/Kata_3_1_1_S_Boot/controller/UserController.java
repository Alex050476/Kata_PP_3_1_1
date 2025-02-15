package com.agor.Kata_3_1_1_S_Boot.controller;

import com.agor.Kata_3_1_1_S_Boot.models.User;
import com.agor.Kata_3_1_1_S_Boot.service.Userservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
//@RequestMapping("/")
public class UserController {

    private final Userservice service;
    private final Userservice userservice;

    @Autowired
    public UserController(Userservice service, Userservice userservice) {
        this.service = service;
        this.userservice = userservice;
    }

    @GetMapping(value = "/")
    public String showUsers(Model model) {
        model.addAttribute("users", service.usersList());
        return "users";
    }

    @GetMapping("/userAdd")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "/userAdd";
    }

    @PostMapping
    public String add(@ModelAttribute ("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "userAdd";
        }
        userservice.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/user")
    public String ref(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userservice.show(id));
        return "ref";
    }

    @PatchMapping("/{id}")
    public String update (@ModelAttribute("user") @Valid User user, BindingResult result, @RequestParam("id") int id) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "ref";
        }
        userservice.refactorUser(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@RequestParam("id") int id) {
        userservice.deleteUser(id);
        return "redirect:/";
    }
}
