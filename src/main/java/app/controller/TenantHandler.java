package app.controller;

import app.model.Apartment;
import app.service.ApartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tenant")
public class TenantHandler {

    private final ApartmentService service;

    public TenantHandler(ApartmentService service) {
        this.service = service;
    }

    @GetMapping
    public String tenantPage(@RequestParam(required = false) Integer rooms,
                             @RequestParam(required = false) String layout,
                             @RequestParam(required = false) Double minPrice,
                             @RequestParam(required = false) Double maxPrice,
                             Model model) {

        List<Apartment> apartments = service.all();


        List<Apartment> filtered = apartments.stream()
                .filter(a -> rooms == null || rooms == 0 || a.getRooms() == rooms)
                .filter(a -> layout == null || layout.equals("any") || a.getLayoutType().equals(layout))
                .filter(a -> minPrice == null || a.getPrice() >= minPrice)
                .filter(a -> maxPrice == null || a.getPrice() <= maxPrice)
                .toList();

        model.addAttribute("apartments", filtered);
        model.addAttribute("rooms", rooms != null ? rooms : 0);
        model.addAttribute("layout", layout != null ? layout : "any");
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);

        return "tenant";
    }
}
