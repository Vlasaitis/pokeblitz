package com.example.pokeblitz.Repositories;

import com.example.pokeblitz.Classes.BattlePokemon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattlePokemonRepository extends CrudRepository<BattlePokemon, Long> {

}
