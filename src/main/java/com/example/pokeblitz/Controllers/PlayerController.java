package com.example.pokeblitz.Controllers;

import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Config.SecurityConfig;
import com.example.pokeblitz.Repositories.PlayerRepository;
import com.example.pokeblitz.Repositories.StartersRepository;
import com.example.pokeblitz.Services.PackService;
import com.example.pokeblitz.Services.PlayerService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @Autowired
    PackService packService;

    @Autowired
    SecurityConfig securityConfig;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private StartersRepository startersRepository;

    @GetMapping("/")
    public String home(HttpSession session) {
       /* boolean loggedIn = Boolean.TRUE == session.getAttribute("loggedIn");
        if (loggedIn) {
            return "/profile";
        }*/
        return "home";

    }

//    @PostMapping("/")
//    public String login(HttpSession session, @RequestParam String username, @RequestParam String password) {
////        List<Player> allPlayers = playerService.getAllPlayers();
//        Player player = playerService.findUser(username);
//        if (player.getPassword().equals(password)) {
//            session.setAttribute("password", password);
//            session.setAttribute("loggedIn", Boolean.TRUE);
//            session.setAttribute("player", player);
//            return "redirect:/profile";
//        }
//        return "redirect:/";
//
//    }

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
    public String registerUser(@Valid Player player, BindingResult bindingResult, HttpSession session) {

        Player checkUsername = playerService.findUser(player.getUsername());
        if (checkUsername != null) {
            bindingResult.rejectValue("username", "username taken/invalid", "Username Taken!");
        }
        if (bindingResult.hasErrors()) {
            return "register";
        }

        /*String password = player.getPassword();
        String confirmPassword = player.getConfirmPassword();
        if(!password.equals(confirmPassword)) {
            bindingResult.rejectValue("password", "error.password", "Passwords do not match");
            return "register";
        }*/


        // ge starter pack till ny user
        Player loggedInPlayer = playerService.savePlayer(new Player(player.getUsername(), player.getPassword()));
        packService.addPackToPlayerInventoryAndDeductCoins(loggedInPlayer, 3, 300, 1);

        securityConfig.createNewUser(player.getUsername(), player.getPassword());

//        System.out.println("newly created pack, is used: " + packService.getPackById(player.getPacks().get(0).getId()).isUsed());

//        session.setAttribute("player", loggedInPlayer); dont think we need this, not yet logged in

        return "redirect:/home";
    }

//    @GetMapping("/profile")
//    public String profile(HttpSession session) {
//        return "profile";
//    }
    @GetMapping("/landingPage")
    public String successfulLogin(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Player player = playerService.findUser(currentPrincipalName);
//        System.out.println(player.getUsername());
        player = playerRepository.findById(player.getId()).get();
        player.setStarters(startersRepository.findByPlayerId(player.getId()));
//        player.getStarters().stream().forEach(battlePokemon -> System.out.println(battlePokemon.getName()));
        if (!player.getAllPokemon().isEmpty() && player.getStarters() != null) {
            player.setBattleStarters(player.getStarters().returnStarters());
        }
//        session.setAttribute("starters", player.getStarters());
        session.setAttribute("player", player);
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        if (!player.getAllPokemon().isEmpty() && player.getStarters() != null) {
            player.setBattleStarters(player.getStarters().returnStarters());
        }
        session.setAttribute("player", player);
        return "profile";
    }

    @GetMapping("/ladder")
    public String sortLadder(HttpSession session, Model model) {
        List<Player> sortedLadder = playerService.sortPlayersByRanking();
        model.addAttribute("ladder", sortedLadder);
        return "ladder";
    }



    @GetMapping("/about")
    public String aboutus(){
        return "about";
    }




}
