package com.example.pokeblitz.Controllers;

import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Services.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BattleController {
    @Autowired
    BattleService battleService;

    @GetMapping("/battle")
    String test() {
        List<BattlePokemon> pokemons1 = List.of(new BattlePokemon(1, "pikachu"),new BattlePokemon(2, "wartortle"),new BattlePokemon(3, "butterfree"));
        List<BattlePokemon> pokemons2 = List.of(new BattlePokemon(1, "blastoise"),new BattlePokemon(2, "caterpie"),new BattlePokemon(3, "mew"));
        Player player1 = new Player(1, "tony", pokemons1);
        Player player2 = new Player(2, "vytis", pokemons2);
        List<String> battleLog = battleService.simulateBattle(player1, player2);
        return "test";
    }
}
