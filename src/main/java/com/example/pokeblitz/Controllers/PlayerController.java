package com.example.pokeblitz.Controllers;

import com.example.pokeblitz.Classes.Battle;
import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Pack;
import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Config.SecurityConfig;
import com.example.pokeblitz.Services.PackService;
import com.example.pokeblitz.Services.PlayerService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @Autowired
    PackService packService;

    @Autowired
    SecurityConfig securityConfig;

    @GetMapping("/")
    public String home(HttpSession session) {

        return "home";

    }


    @GetMapping("/login")
    public String login() {
        return "home";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("player", new Player());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid Player player, BindingResult bindingResult, HttpSession session)
     {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        // ge starter pack till ny user
        Player loggedInPlayer = playerService.savePlayer(new Player(player.getUsername(), player.getPassword()));
        Pack pack = packService.savePack(new Pack(3,100,loggedInPlayer, 1));
        playerService.savePlayer(loggedInPlayer.addPurchasedPack(pack));
        securityConfig.createNewUser(player.getUsername(), player.getPassword());
//        model.addAttribute("packss", loggedInPlayer.getPacks());
//         session.setAttribute("packs", loggedInPlayer.getPacks());
        session.setAttribute("player", loggedInPlayer);
        session.setAttribute("username", player.getUsername());
        return "redirect:/profile";
    }


    @GetMapping("/profile")
    public String profile(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        session.setAttribute("player", playerService.findUser(currentPrincipalName));
        return "profile";
    }






}
