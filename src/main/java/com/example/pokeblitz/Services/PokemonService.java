package com.example.pokeblitz.Services;

import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Repositories.BattlePokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {
    @Autowired
    BattlePokemonRepository pokemonRepository;

    public BattlePokemon savePokemon(BattlePokemon pokemon, Player player) {
        pokemon.setPlayer(player);
        return pokemonRepository.save(pokemon);
    }
}
