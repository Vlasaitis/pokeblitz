package com.example.pokeblitz.Controllers;

import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Player;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
    public String addStartersToProfile(@RequestParam("pokemonIds") List<String> pokemonIds, HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        List<BattlePokemon> starters = new ArrayList<>();
        for (int i = 0; i < pokemonIds.size(); i++) {
            starters.add(pokemonService.findById(Long.valueOf(pokemonIds.get(i))));
        }
        player.setStarters(starters);
        playerService.savePlayer(player);
        return "profile";
    }


}
