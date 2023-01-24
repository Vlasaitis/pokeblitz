package com.example.pokeblitz;


import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Services.BattleService;
import com.github.oscar0812.pokeapi.models.pokemon.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.pokeblitz.Controllers.BattleController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PokeBlitzTests {

	@Autowired
	BattleService battleService;

	@Test
	void contextLoads() {
	}

	@Test
	public void battlePokemonConstructorTest() {
		BattlePokemon testPokemon = new BattlePokemon(1, "bulbasaur");

		assertEquals(45, testPokemon.getHp());
		assertEquals(49, testPokemon.getAttack());
		assertEquals(49, testPokemon.getDefense());
		assertEquals(45, testPokemon.getSpeed());
		assertEquals(45, testPokemon.getHp());

		assertEquals("grass", testPokemon.getTypes().get(0));
		assertEquals("poison", testPokemon.getTypes().get(1));

		String[] doubleDmg = {"ground", "rock", "water", "grass", "fairy"};
		String[] testDbl = testPokemon.getDoubleDamage().toArray(new String[5]);
		Assertions.assertArrayEquals(doubleDmg, testDbl);

		String[] halfDmg = {"flying", "poison", "bug", "steel", "fire", "grass", "dragon", "poison", "ground", "rock", "ghost"};
		String[] testHalf = testPokemon.getHalfDamage().toArray(new String[11]);
		Assertions.assertArrayEquals(halfDmg, testHalf);
	}

	@Test
	public void battleDetermineOrderTest() {
		List<BattlePokemon> pokemons1 = new ArrayList<>(Arrays.asList(new BattlePokemon(1, "pikachu"), new BattlePokemon(2, "wartortle"), new BattlePokemon(3, "butterfree")));
		List<BattlePokemon> pokemons2 = new ArrayList<>(Arrays.asList(new BattlePokemon(1, "blastoise"), new BattlePokemon(2, "caterpie"), new BattlePokemon(3, "mew")));
		Player attacker = new Player(1, "tony", pokemons1);
		Player defender = new Player(2, "vytis", pokemons2);

		boolean attackerStarts = battleService.doesAttackerStart(attacker, defender);
		assertEquals(false, attackerStarts);

		defender.getStarters().remove(2);
		defender.getStarters().add(new BattlePokemon(3, "charmander"));
		boolean attackStarts2 = battleService.doesAttackerStart(attacker, defender);

		assertEquals(true, attackStarts2);

	}

	@Test
	public void findFastestWithTurnTest() {
		BattlePokemon blastoise = new BattlePokemon(1, "blastoise");
		BattlePokemon caterpie = new BattlePokemon(1, "caterpie");
		BattlePokemon mew = new BattlePokemon(1, "mew");
		List<BattlePokemon> pokemons = Arrays.asList(blastoise, caterpie, mew);

		BattlePokemon fastestWithTurn = battleService.fastestWithTurn(pokemons);
		assertEquals(mew, fastestWithTurn);

		mew.setHasTurn(false);
		assertEquals(blastoise, battleService.fastestWithTurn(pokemons));

		blastoise.setHasTurn(false);
		assertEquals(caterpie, battleService.fastestWithTurn(pokemons));

		caterpie.setHasTurn(false);
		assertEquals(mew, battleService.fastestWithTurn(pokemons));

	}

	@Test
	public void simulateBattleTest() {
		List<BattlePokemon> pokemons1 = new ArrayList<>(Arrays.asList(new BattlePokemon(1, "pikachu"), new BattlePokemon(2, "wartortle"), new BattlePokemon(3, "butterfree")));
		List<BattlePokemon> pokemons2 = new ArrayList<>(Arrays.asList(new BattlePokemon(1, "blastoise"), new BattlePokemon(2, "caterpie"), new BattlePokemon(3, "mew")));
		Player player1 = new Player(1, "tony", pokemons1);
		Player player2 = new Player(2, "vytis", pokemons2);
		List<String> battleLog = battleService.simulateBattle(player1, player2);
		for (int i = 0; i < battleLog.size(); i++) {
			System.out.println(battleLog.get(i));
			System.out.println();
		}

	}
}
