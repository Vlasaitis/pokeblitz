package com.example.pokeblitz.Controllers;

import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Classes.Starters;
import com.example.pokeblitz.Repositories.StartersRepository;
import com.example.pokeblitz.Services.PackService;
import com.example.pokeblitz.Services.PlayerService;
import com.example.pokeblitz.Services.PokemonService;
import com.example.pokeblitz.Services.StartersService;
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
    @Autowired
    StartersService startersService;

    @GetMapping("/setStarters")
    public String home(HttpSession session) {
        return "setStarters";
    }

    @PostMapping("/addStartersToProfile")
    public String addStartersToProfile(@RequestParam("pokemonIds") List<String> pokemonIds, HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        List<BattlePokemon> starters = new ArrayList<>();
        for (int i = 0; i < pokemonIds.size(); i++) {
            starters.add(pokemonService.findById(Long.valueOf(pokemonIds.get(i))));
        }

        Starters starterz = new Starters(player, starters.get(0), starters.get(1),starters.get(2)); // nytt
        startersService.saveStarters(starterz); // nytt

        player.setStarters(starterz);
        player.setBattleStarters(starters);
        playerService.savePlayer(player);

        session.setAttribute("player", player);
//        session.setAttribute("starters", starters); // nytt
        return "redirect:/profile";
    }


}
