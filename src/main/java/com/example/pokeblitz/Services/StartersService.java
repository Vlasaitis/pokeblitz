package com.example.pokeblitz.Services;

import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Classes.Starters;
import com.example.pokeblitz.Repositories.StartersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartersService {

    @Autowired
    StartersRepository startersRepository;

    public Starters saveStarters(Starters starters) {
        return startersRepository.save(starters);
    }
    public Starters getStartersByPlayerId(Long id) {
        return startersRepository.findByPlayerId(id);
    }
    
}
