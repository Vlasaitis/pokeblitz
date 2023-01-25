package com.example.pokeblitz.Controllers;

import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Services.PlayerService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @GetMapping("/")
    public String home(HttpSession session) {
        boolean loggedIn = Boolean.TRUE == session.getAttribute("loggedIn");
        if (loggedIn) {
            return "/profile";
        }
        return "home";

    }

    @PostMapping("/")
    public String login(HttpSession session, @RequestParam String username, @RequestParam String password) {
        List<Player> allPlayers = playerService.getAllPlayers();
        for (Player player : allPlayers) {
            if (username.equals(player.getUsername().toLowerCase()) && password.equals(player.getPassword())) {
                String name = username.substring(0, 1).toUpperCase() + username.substring(1);
                session.setAttribute("username", name);
                session.setAttribute("password", password);
                session.setAttribute("loggedIn", Boolean.TRUE);
                session.setAttribute("player", player);
                return "redirect:/profile";
            }
        }
        return "redirect:/";

    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("player", new Player());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Player player, BindingResult bindingResult, HttpSession session, Model model)
     {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        playerService.savePlayer(player);
        model.addAttribute("player", player);
        session.setAttribute("player", player);
        session.setAttribute("username", player.getUsername());
        session.setAttribute("loggedIn", Boolean.TRUE);
        //PlayerService.savePlayer(player);
        return "redirect:/profile/" + player.getUsername();
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/profile/{username}")
    public String profile(Model model, @PathVariable String username, HttpSession session) {
       // Player player = PlayerService.findUser(username);
        model.addAttribute("player", session.getAttribute("player"));
        if (session.getAttribute("username").equals(username)) {
            return "profile";
        }
        return "profile";
    }



}
