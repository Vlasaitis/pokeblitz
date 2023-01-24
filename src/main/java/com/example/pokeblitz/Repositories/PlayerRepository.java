package com.example.pokeblitz.Repositories;

import com.example.pokeblitz.Classes.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
    Player findByusername(String username);
}
