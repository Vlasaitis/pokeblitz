package com.example.pokeblitz.Repositories;

import com.example.pokeblitz.Classes.Starters;
import org.springframework.data.repository.CrudRepository;

public interface StartersRepository extends CrudRepository<Starters, Long> {
    Starters findByPlayerId(Long playerId);

}
