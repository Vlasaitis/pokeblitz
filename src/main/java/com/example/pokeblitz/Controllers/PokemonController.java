package com.example.pokeblitz.Controllers;

import com.example.pokeblitz.Services.PackService;
import com.example.pokeblitz.Services.PlayerService;
import com.example.pokeblitz.Services.PokemonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PokemonController {

    @Autowired
    PlayerService playerService;

    @Autowired
    PokemonService pokemonService;

    @GetMapping("/setStarters")
    public String home(HttpSession session) {
        return "setStarters.html";
    }

    @PostMapping("/addStartersToProfile")
    public String approveStarters(HttpSession session) {

        return "profile";
    }

}
