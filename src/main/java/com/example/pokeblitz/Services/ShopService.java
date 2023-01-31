package com.example.pokeblitz.Services;

import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    @Autowired
    PokemonService pokemonService;
    @Autowired
    PlayerService playerService;


    public boolean canPlayerAffordPurchase(Player player, String packType, int amount) {
        if (totalCost(packType, amount) <= player.getCoins()) {
            return true;
        }
        return false;
    }

    public int totalCost(String packType, int amount) {
        int totalPrice = switch (packType) {
            case "epic" -> 600;
            case "rare" -> 400;
            case "uncommon" -> 200;
            case "common" -> 100;
            default -> 100000;
        };
        totalPrice = totalPrice*amount;
        return totalPrice;
    }

    public void attemptSale(Player player, Long pokemonId) {
        if (player.getAllPokemon().size() >= 4) {
            BattlePokemon pokemonToSell = pokemonService.findById(pokemonId);
            player.addCoins(pokemonToSell.getPrice());
            player.getAllPokemon().remove(pokemonToSell);
            playerService.savePlayer(player);
            System.out.println(player.getAllPokemon().size());
            pokemonService.deletePokemon(pokemonToSell);
        }
    }
}
