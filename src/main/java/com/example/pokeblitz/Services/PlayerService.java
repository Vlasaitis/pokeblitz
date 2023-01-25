package com.example.pokeblitz.Services;

import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        List<Player> allPlayer = (List<Player>) playerRepository.findAll();
        return allPlayer;
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player findUser(String username) {
        Player player = playerRepository.findByusername(username);
        return player;
    }


}
