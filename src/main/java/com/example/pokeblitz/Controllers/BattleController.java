package com.example.pokeblitz.Controllers;

import com.github.oscar0812.pokeapi.models.pokemon.Pokemon;
import com.github.oscar0812.pokeapi.utils.Client;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BattleController {

    @GetMapping("/")
    String test() {
        System.out.println(Client.getPokemonByName("bulbasaur"));
        return "test";
    }
}
