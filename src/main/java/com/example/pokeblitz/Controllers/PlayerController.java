package com.example.pokeblitz.Controllers;

import com.example.pokeblitz.Classes.Battle;
import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Pack;
import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Services.PackService;
import com.example.pokeblitz.Services.PlayerService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @Autowired
    PackService packService;

    @GetMapping("/")
    public String home(HttpSession session) {
       /* boolean loggedIn = Boolean.TRUE == session.getAttribute("loggedIn");
        if (loggedIn) {
            return "/profile";
        }*/
        return "home";

    }

    @PostMapping("/")
    public String login(HttpSession session, @RequestParam String username, @RequestParam String password) {
//        List<Player> allPlayers = playerService.getAllPlayers();
        Player player = playerService.findUser(username);
        if (player.getPassword().equals(password)) {
            session.setAttribute("password", password);
            session.setAttribute("loggedIn", Boolean.TRUE);
            session.setAttribute("player", player);
            return "redirect:/profile";
        }
        return "redirect:/";
//        for (Player player : allPlayers) {
//            if (username.equals(player.getUsername()) && password.equals(player.getPassword())) {
////                String name = username.substring(0, 1).toUpperCase() + username.substring(1);
////                session.setAttribute("username", name);
//                session.setAttribute("password", password);
//                session.setAttribute("loggedIn", Boolean.TRUE);
//                session.setAttribute("player", player);
//                return "redirect:/profile";
//            }
//        }

    }

    @GetMapping("/login")
    public String login() {
        return "home";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("player", new Player());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid Player player, BindingResult bindingResult, HttpSession session, Model model)
     {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        // ge starter pack till ny user
        Player loggedInPlayer = playerService.savePlayer(new Player(player.getUsername(), player.getPassword()));
        Pack pack = packService.savePack(new Pack(3,200,loggedInPlayer, 2));
        playerService.savePlayer(loggedInPlayer.addPurchasedPack(pack));

//        model.addAttribute("packss", loggedInPlayer.getPacks());
//         session.setAttribute("packs", loggedInPlayer.getPacks());
        session.setAttribute("player", loggedInPlayer);
        session.setAttribute("username", player.getUsername());
        session.setAttribute("loggedIn", Boolean.TRUE);
        //PlayerService.savePlayer(player);
        return "redirect:/profile/" + player.getUsername();
    }

    @GetMapping("/profile")
    public String profile(HttpSession session) {
        return "profile";
    }

    @GetMapping("/profile/{username}")
    public String profile(Model model, @PathVariable String username, HttpSession session) {
       // Player player = PlayerService.findUser(username);
        model.addAttribute("player", session.getAttribute("player"));
        if (session.getAttribute("username").equals(username)) {
            return "profile";
        }
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }




}
