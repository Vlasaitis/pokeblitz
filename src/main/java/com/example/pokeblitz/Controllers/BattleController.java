package com.example.pokeblitz.Controllers;

import com.example.pokeblitz.Services.BattleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BattleController {
    @Autowired
    BattleService battleService;

    @GetMapping("/battle")
    String test(HttpSession session) {
        List<String> battleLog = new ArrayList<>();
        battleLog.add("first entry");
        battleLog.add("first entry");
        battleLog.add("first entry");
        battleLog.add("first entry");
        battleLog.add("first entry");
        battleLog.add("first entry");
        battleLog.add("first entry");
        battleLog.add("first entry");
        battleLog.add("first entry");
        battleLog.add("first entry");
        battleLog.add("first entry");
        battleLog.add("first entry");
        battleLog.add("first entry");
        battleLog.add("first entry");
        battleLog.add("first entry");
        battleLog.add("first entry");
        session.setAttribute("battleLog", battleLog);
        return "battleSimulation";
    }

    @GetMapping("/test123")
    String test2(Model model){
        return "test123";
    }

}
