package com.example.pokeblitz.Repositories;

import com.example.pokeblitz.Classes.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    Player findByUserName(String username);

}
