package com.example.pokeblitz;


import Classes.BattlePokemon;
import com.github.oscar0812.pokeapi.models.pokemon.Pokemon;
import com.github.oscar0812.pokeapi.utils.Client;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PokeblitzApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void damageMultiplier(){
		Pokemon grabPokemon = Client.getPokemonByName("Pikachu");
		BattlePokemon attacker = new BattlePokemon();


	}
}
