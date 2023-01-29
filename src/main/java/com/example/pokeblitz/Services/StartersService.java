package com.example.pokeblitz.Services;

import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Classes.Starters;
import com.example.pokeblitz.Repositories.StartersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StartersService {

    @Autowired
    StartersRepository startersRepository;
    @Autowired
    PlayerService playerService;

    public Starters saveStarters(Starters starters) {
        return startersRepository.save(starters);
    }
    public Starters getStartersByPlayerId(Long id) {
        return startersRepository.findByPlayerId(id);
    }

    public void updateStartersEntryOrCreateItAndUpdateBattleStarters(Player player, List<BattlePokemon> starters) {
        if (player.getStarters() == null) { // if starters object hasnt been initialized yet for this player and added to DB: first time you register
            Starters newStarters = saveStarters(new Starters(player, starters));
            player.setStarters(newStarters);
        } else {
            Starters newStarters = player.getStarters();
            newStarters.setNewStarters(starters.get(0), starters.get(1),starters.get(2)); // otherwise just use this custom setter
            saveStarters(newStarters);
            player.setStarters(newStarters);
        }
        player.setBattleStarters(player.getStarters().returnStarters());
        playerService.savePlayer(player);
    }
}
