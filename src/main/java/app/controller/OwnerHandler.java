package app.controller;

import app.model.Apartment;
import app.model.User;
import app.service.ApartmentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owner")
public class OwnerHandler {
    private final ApartmentService service;

    public OwnerHandler(ApartmentService service) {
        this.service = service;
    }




    @GetMapping
    public String ownerPage(HttpSession session, Model model) {
        User u = (User) session.getAttribute("user");
        if (u == null) return "redirect:/";
        model.addAttribute("user", u);
        model.addAttribute("apartments", service.byOwner(u.getUsername()));
        return "owner";
    }


    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("apartment", new Apartment());
        return "apartment_form";
    }

    @PostMapping("/save")
    public String saveApartment(@ModelAttribute Apartment apartment, HttpSession session) {
        User u = (User) session.getAttribute("user");
        if (u == null) return "redirect:/";
        apartment.setOwnerUsername(u.getUsername());
        service.save(apartment);
        return "redirect:/owner";
    }


    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        service.findById(id).ifPresent(a -> model.addAttribute("apartment", a));
        return "apartment_form";
    }


    @GetMapping("/delete/{id}")
    public String confirmDelete(@PathVariable Long id, Model model) {
        service.findById(id).ifPresent(a -> model.addAttribute("apartment", a));
        return "confirm_delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteApartment(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/owner";
    }
}
