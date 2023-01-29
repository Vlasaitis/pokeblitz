package com.example.pokeblitz.Controllers;

import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Pack;
import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Services.PackService;
import com.example.pokeblitz.Services.PlayerService;
import com.example.pokeblitz.Services.PokemonService;
import com.example.pokeblitz.Services.StartersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Autowired
    PokemonService pokemonService;
    @Autowired
    StartersService startersService;


    @GetMapping("/packOpening")
    public String choosePackToOpen(HttpSession session) {
        Player loggedInPlayer = (Player) session.getAttribute("player");
        List<Pack> unopenedPacks = new ArrayList<>();
        loggedInPlayer.getPacks().stream().forEach(pack -> { if (!pack.isUsed()) { unopenedPacks.add(pack);}}); // think this is right but was TIRED
        session.setAttribute("playerPacks", unopenedPacks);
        return "packOpening";
    }
    @GetMapping("/openedPack")
    public String test() {
        return "openedPack";
    }

    @PostMapping("/packOpening")
    public String openPack(HttpSession session, @RequestParam Long packId) {
        Player player = (Player) session.getAttribute("player");
        boolean starterPack = startersService.isStarterPack(player);
        List<BattlePokemon> openedPokemon = packService.openPackAndUpdateDB(packId, player);
        if (starterPack) {
            startersService.updateStartersEntryOrCreateItAndUpdateBattleStarters(player, openedPokemon); // if first ever pack, immediately sets this pack pokemon as starters
        }
        session.setAttribute("loot", openedPokemon);
        return "redirect:/openedPack";
    }

}

