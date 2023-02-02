package com.example.pokeblitz.Services;

import com.example.pokeblitz.Classes.Battle;
import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Classes.Starters;
import com.example.pokeblitz.Repositories.BattlePokemonRepository;
import com.example.pokeblitz.Repositories.StartersRepository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class PokemonService {
    @Autowired
    BattlePokemonRepository pokemonRepository;
    @Autowired
    StartersRepository startersRepository;

    public BattlePokemon savePokemon(BattlePokemon pokemon, Player player) {
        pokemon.setPlayer(player);
        return pokemonRepository.save(pokemon);
    }
    public void deletePokemon(BattlePokemon pokemon) {
        pokemonRepository.deleteById(pokemon.getId());
    }
    public void levelUp(BattlePokemon poke) {
        if(poke.exp == 0){
            poke.setAttack(poke.getAttack()+1);
            poke.setDefense(poke.getDefense()+1);
            poke.setMaxHp(poke.getMaxHp()+1);
            poke.setSpeed(poke.getSpeed()+1);
            poke.setPowerLevel(poke.getPowerLevel()+4);
            poke.setLevel(poke.getLevel() + 1);
        }

    }
    public BattlePokemon findById(Long id) {
        return pokemonRepository.findById(id).get();
    }


    public Starters saveStarters(Player player, BattlePokemon pokemon1, BattlePokemon pokemon2, BattlePokemon pokemon3){
      Starters starters = new Starters(player, pokemon1, pokemon2, pokemon3);
      return startersRepository.save(starters);
    }

    public List<BattlePokemon> getStartersListByIds(List<String> pokemonIds) {
        List<BattlePokemon> starters = new ArrayList<>();
        for (int i = 0; i < pokemonIds.size(); i++) {
            starters.add(findById(Long.valueOf(pokemonIds.get(i))));
        }
        return starters;
    }
}
