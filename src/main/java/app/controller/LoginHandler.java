package app.controller;

import app.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginHandler {
    private final AuthService authService;

    public LoginHandler(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String loginForm(Model model) {
        model.addAttribute("loginError", false);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        var userOpt = authService.authenticate(username, password);
        if (userOpt.isPresent()) {
            var user = userOpt.get();
            session.setAttribute("user", user);
            return switch (user.getRole()) {
                case OWNER -> "redirect:/owner";
                case TENANT -> "redirect:/tenant";
            };
        } else {
            model.addAttribute("loginError", true);
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
