package com.example.pokeblitz.Services;

import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
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


    public List<Player> sortPlayersByRanking() {
        List<Player> allPlayers = (List<Player>) playerRepository.findAll();
        allPlayers.sort(Comparator.comparingInt(Player::getElo).reversed());
        // alternatively can use the below. keeping this here while trying the above. Can delete if leaderboard works
//        Collections.sort(allPlayers, new Comparator<Player>() {
//            @Override
//            public int compare(Player p1, Player p2) {
//                return Integer.compare(p2.getElo(), p1.getElo());
//            }
//        });
        return allPlayers;
    }
}
