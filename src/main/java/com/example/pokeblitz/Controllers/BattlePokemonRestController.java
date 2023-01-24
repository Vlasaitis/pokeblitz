package com.example.pokeblitz.Controllers;

import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Repositories.BattlePokemonRepository;
import com.example.pokeblitz.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class BattlePokemonRestController {

    @Autowired
    private BattlePokemonRepository battlePokemonRepository;

    // Get all BattlePokemons
    @GetMapping
    public List<BattlePokemon> getAllBtPokemons() {
        return this.battlePokemonRepository.findAll();
    }

    // Get BattlePokemon by ID
    @GetMapping("/{id}")
    public BattlePokemon getBtPokemonById(@PathVariable(value = "id") int btPokemonId) {
        return this.battlePokemonRepository.findById(btPokemonId)
                .orElseThrow(() -> new ResourceNotFoundException("BattlePokemon not found with id :" + btPokemonId));
    }

    // Create BattlePokemon
    @PostMapping
    public BattlePokemon createBtPokemon(@RequestBody BattlePokemon btPokemon) {
        return this.battlePokemonRepository.save(btPokemon);
    }

    // Update BattlePokemon by ID
    @PutMapping("/{id}")
    public BattlePokemon updateBtPokemon(@RequestBody BattlePokemon btPokemon, @PathVariable ("id") int btPokemonId) {
        BattlePokemon existingBtPokemon = this.battlePokemonRepository.findById(btPokemonId)
                .orElseThrow(() -> new ResourceNotFoundException("BattlePokemon not found with id :" + btPokemonId));
        existingBtPokemon.setName(btPokemon.getName());
        existingBtPokemon.setAttack(btPokemon.getAttack());
        existingBtPokemon.setDefense(btPokemon.getDefense());
        return this.battlePokemonRepository.save(existingBtPokemon);
    }

    // Delete BattlePokemon by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<BattlePokemon> deleteBtPokemon(@PathVariable ("id") int btPokemonId){
        BattlePokemon existingBtPokemon = this.battlePokemonRepository.findById(btPokemonId)
                .orElseThrow(() -> new ResourceNotFoundException("BattlePokemon not found with id :" + btPokemonId));
        this.battlePokemonRepository.delete(existingBtPokemon);
        return ResponseEntity.ok().build();
    }
}
