package com.example.pokeblitz.Services;

import com.example.pokeblitz.Classes.Player;
import org.springframework.stereotype.Service;

@Service
public class ShopService {


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
}
