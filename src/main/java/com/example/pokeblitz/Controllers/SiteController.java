package com.example.pokeblitz.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SiteController {
    @GetMapping("/")
    String getAll(Model model){

        return "register";
    }


}
