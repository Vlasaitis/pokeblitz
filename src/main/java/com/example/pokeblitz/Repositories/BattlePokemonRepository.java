package com.example.pokeblitz.Repositories;

import com.example.pokeblitz.Classes.BattlePokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattlePokemonRepository extends JpaRepository<BattlePokemon, Long> {

}
