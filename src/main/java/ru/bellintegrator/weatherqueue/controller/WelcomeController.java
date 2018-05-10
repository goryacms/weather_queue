package ru.bellintegrator.weatherqueue.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
    @RequestMapping(value = "/")
    public String welcome(Model model) {
        model.addAttribute("title", "Погодный брокер");
        return "index";
    }
}