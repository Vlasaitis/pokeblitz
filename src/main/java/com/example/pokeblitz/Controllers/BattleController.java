package com.example.pokeblitz.Controllers;

import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Repositories.PlayerRepository;
import com.example.pokeblitz.Services.BattleService;
import com.example.pokeblitz.Services.PlayerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class BattleController {
    @Autowired
    BattleService battleService;
    @Autowired
    private PlayerService playerService;

    @GetMapping("/battle")
    String setUpBattle(HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        player.setBattleStarters(player.getStarters().returnStarters());
        session.setAttribute("player", player);
//        playerService.savePlayer(player);
//        session.setAttribute("player", playerService.findUser(player.getUsername()));
        if (session.getAttribute("battleLog") != null) {
            session.removeAttribute("battleLog");
        }
        return "battle";
    }

    @GetMapping("/battleSimulation")
    String simulateBattle(HttpSession session) {
        Player attacker = (Player) session.getAttribute("player");
        Player defender = battleService.findRandomOpponent(attacker);
        List<String> battleLog = battleService.simulateBattle(attacker, defender);

        session.setAttribute("attackerStarters", attacker.getStarters());
        session.setAttribute("defenderStarters", defender.getStarters());
        session.setAttribute("defenderName", defender.getUsername());
        session.setAttribute("battleLog", battleLog);
        return "battleSimulation";
    }
    @GetMapping("/endBattle")
    String endBattle(HttpSession session) {
        session.removeAttribute("attacker");
        session.removeAttribute("defender");
        return "redirect:/profile";
    }

}
