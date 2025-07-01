package Supplement_store.Suplement_store.controllers;

import Supplement_store.Suplement_store.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        try {
            User user = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();

            if (user.getPassword().equals(password)) {
                session.setAttribute("role", user.getRole().toString());
                return "redirect:/dashboard";
            } else {
                model.addAttribute("error", "Invalid credentials");
            }
        } catch (Exception e) {
            model.addAttribute("error", "User not found");
        }

        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
