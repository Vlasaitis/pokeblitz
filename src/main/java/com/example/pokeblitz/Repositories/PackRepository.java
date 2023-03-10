package com.example.pokeblitz.Repositories;

import com.example.pokeblitz.Classes.Pack;
import com.example.pokeblitz.Classes.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackRepository extends CrudRepository<Pack, Long> {

}
