package com.example.pokeblitz.Services;

import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    static
    PlayerRepository playerRepository;

    public static List<Player> getAllPlayers() {
        List<Player> allPlayer = (List<Player>) playerRepository.findAll();
        return allPlayer;
    }

    public static void savePlayer(Player player) {
        playerRepository.save(player);
    }

    Player findUser(String username) {
        Player player = playerRepository.findByusername(username);
        return player;
    }


}
