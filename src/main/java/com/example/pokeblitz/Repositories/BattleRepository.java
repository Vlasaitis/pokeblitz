package com.example.pokeblitz.Repositories;

import com.example.pokeblitz.Classes.Battle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattleRepository extends CrudRepository<Battle, Long> {

}