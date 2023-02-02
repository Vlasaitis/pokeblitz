package com.example.pokeblitz.Controllers;

import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Services.PlayerService;
import com.example.pokeblitz.Services.PokemonService;
import com.example.pokeblitz.Services.StartersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        startersService.cleanTanks(player);
        List<BattlePokemon> starters = pokemonService.getStartersListByIds(pokemonIds);
        startersService.updateStartersEntryOrCreateItAndUpdateBattleStarters(player, starters);
        session.setAttribute("player", playerService.findUser(player.getUsername()));
        return "redirect:/profile";
    }
    @PostMapping("/changeTank")
    public String changeTank(HttpSession session, @RequestParam String tankName) { //@PathVariable Long pokemonId
        System.out.println(tankName);
        Player player = (Player) session.getAttribute("player");
        startersService.changeTank(player, tankName);
        session.setAttribute("player", playerService.findUser(player.getUsername()));
        return "redirect:/profile";
    }



}
