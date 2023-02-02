package com.example.pokeblitz.Config;

import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class StartupUserAuth implements CommandLineRunner {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public void run(String...args) throws Exception {

        List <Player> allPlayers = (List<Player>) playerRepository.findAll();

        for(Player player : allPlayers){
            String encodedPassword = encoder.encode(player.getPassword());
            ((InMemoryUserDetailsManager) userDetailsService).createUser(User.withUsername(player.getUsername()).password(encodedPassword).roles("USER").build());
        }

    }

}
