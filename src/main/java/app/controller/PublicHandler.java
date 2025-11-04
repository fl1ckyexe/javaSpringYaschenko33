package app.controller;

import app.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicHandler {


    @Autowired
    private ApartmentService service;


    @GetMapping("/public")
    public String publicPage(Model model) {
        model.addAttribute("apartments", service.all());
        return "public";
    }
}
