package com.example.pokeblitz;


import com.example.pokeblitz.Classes.BattlePokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PokeBlitzTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void battlePokemonConstructor(){
		BattlePokemon testPokemon = new BattlePokemon(1, "bulbasaur");

		assertEquals(45, testPokemon.getHp());
		assertEquals(49, testPokemon.getAttack());
		assertEquals(49, testPokemon.getDefense());
		assertEquals(45, testPokemon.getSpeed());
		assertEquals(45, testPokemon.getHp());

		assertEquals("grass", testPokemon.getTypes().get(0));
		assertEquals("poison", testPokemon.getTypes().get(1));

//        List<String> doubleDmg = List.of("ground", "rock", "water", "grass", "fairy");
        String[] doubleDmg = {"ground", "rock", "water", "grass", "fairy"};
        String[] testDbl = testPokemon.getDoubleDamage().toArray(new String[5]);
        Assertions.assertArrayEquals(doubleDmg, testDbl);

	}
}
