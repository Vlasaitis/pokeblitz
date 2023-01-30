package com.example.pokeblitz.Controllers;

import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Services.PackService;
import com.example.pokeblitz.Services.PlayerService;
import com.example.pokeblitz.Services.ShopService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShopController {

    @Autowired
    PlayerService playerService;
    @Autowired
    PackService packService;
    @Autowired
    ShopService shopService;


    @GetMapping("/shop")
    public String shop(HttpSession session) {
        return "shop";
    }

    @PostMapping("/processPurchase")
    public String processPurchase(HttpSession session, @RequestParam int amount, @RequestParam String packType) {
        Player player = (Player) session.getAttribute("player");

        if (shopService.canPlayerAffordPurchase(player, packType, amount)) {
            int packPrice = shopService.totalCost(packType, amount);
            packService.addPackToPlayerInventoryAndDeductCoins(player,amount,packPrice,packService.convertTierToInt(packType));
        }

        return "shop";
    }

}
