package com.example.pokeblitz.Controllers;

import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Pack;
import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Services.PackService;
import com.example.pokeblitz.Services.PlayerService;
import com.github.oscar0812.pokeapi.models.pokemon.Pokemon;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PackController {
    @Autowired
    PlayerService playerService;

    @Autowired
    PackService packService;


    @GetMapping("/packOpening")
    public String choosePackToOpen(HttpSession session, Model model) {
        Player loggedInPlayer = (Player) session.getAttribute("player");
        List<Pack> unopenedPacks = new ArrayList<>();
        loggedInPlayer.getPacks().stream().forEach(pack -> { if (!pack.isUsed()) { unopenedPacks.add(pack);}}); // think this is right but was TIRED
        session.setAttribute("playerPacks", unopenedPacks);
        return "packOpening";
    }
    @PostMapping("/packOpening")
    public String openPack(HttpSession session, Model model, @RequestParam Long packId) {
        Player player = (Player) session.getAttribute("player");

        Pack packToBeOpened = packService.getPackById(packId); // gets the pack object to be opened
        List<BattlePokemon> openedPokemon = packService.openPack(packToBeOpened); // opens, and extracts into variable
        openedPokemon.stream().forEach(battlePokemon -> player.getAllPokemon().add(battlePokemon)); // give the pokemon to the user
        packService.savePack(packToBeOpened.setUsed(true)); // set the pack to used and save it in database
        playerService.savePlayer(player);

        session.setAttribute("loot", openedPokemon);
        return "openedPack";
    }

}

