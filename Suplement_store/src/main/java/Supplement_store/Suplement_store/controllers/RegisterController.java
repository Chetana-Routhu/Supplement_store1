package Supplement_store.Suplement_store.controllers;

import Supplement_store.Suplement_store.entities.Role;
import Supplement_store.Suplement_store.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @Transactional
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String confirmPassword,
                           Model model) {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match!");
            return "register";
        }

        
        Long count = entityManager.createQuery(
                "SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class)
                .setParameter("username", username)
                .getSingleResult();

        if (count > 0) {
            model.addAttribute("error", "Username already exists!");
            return "register";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(Role.CUSTOMER);

        entityManager.persist(user);

        return "redirect:/login";
    }
}
