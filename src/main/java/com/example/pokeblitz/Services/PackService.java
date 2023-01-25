package com.example.pokeblitz.Services;

import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Pack;
import com.example.pokeblitz.Classes.Player;
import com.github.oscar0812.pokeapi.models.pokemon.Pokemon;
import com.github.oscar0812.pokeapi.utils.Client;
import org.hibernate.loader.BatchLoadSizingStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Service
public class PackService {
private Random random = new Random();
    public List<BattlePokemon> openPack(Pack pack){
        List<BattlePokemon> tierPack = adjustListBasedOnTier(pack.getTier());
        List<BattlePokemon> packPokemon = new ArrayList<>();

       for (int i = 0; i < pack.getPokemonAmount(); i++) {
            packPokemon.add(tierPack.get(random.nextInt(tierPack.size())));
        }
        return packPokemon;
    }
    private List<BattlePokemon> adjustListBasedOnTier(int tier) {
        List<Pokemon> allPokemon = new ArrayList<>();

        for (int j = 1; j < 152; j++) {
            allPokemon.add(Client.getPokemonById(j));
        }

        List<BattlePokemon> common = new ArrayList<>();
        List<BattlePokemon> uncommon = new ArrayList<>();
        List<BattlePokemon> rare = new ArrayList<>();
        List<BattlePokemon> epic = new ArrayList<>();
        for (int i = 0; i < allPokemon.size(); i++) {
            int powerLevel = allPokemon.get(i).getStats().get(0).getBaseStat() + allPokemon.get(i).getStats().get(1).getBaseStat() + allPokemon.get(i).getStats().get(2).getBaseStat() + allPokemon.get(i).getStats().get(5).getBaseStat();

            if (powerLevel >= 350) {
                epic.add(new BattlePokemon(allPokemon.get(i).getName()));
            } else if (powerLevel >= 300) {
                rare.add(new BattlePokemon(allPokemon.get(i).getName()));
            } else if (powerLevel >= 225) {
                uncommon.add(new BattlePokemon(allPokemon.get(i).getName()));
            } else {
                common.add(new BattlePokemon(allPokemon.get(i).getName()));
            }
        }
        if (tier == 4){
            return epic;
        } else if (tier == 3) {
            return rare;
        } else if (tier == 2) {
            return uncommon;
        } else {
        return common;
        }
    }


}
