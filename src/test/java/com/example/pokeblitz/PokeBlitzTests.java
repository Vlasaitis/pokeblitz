package com.example.pokeblitz;


import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Pack;
import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Services.BattleService;
import com.example.pokeblitz.Services.PackService;
import com.example.pokeblitz.Services.PlayerService;
import com.example.pokeblitz.Services.PokemonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PokeBlitzTests {

	@Autowired
	BattleService battleService;
	@Autowired
	PlayerService playerService;
	@Autowired
	PokemonService pokemonService;
	@Autowired
	PackService packService;


	@Test
	void contextLoads() {
	}

	@Test
	public void battlePokemonConstructorTest() {
		BattlePokemon testPokemon = new BattlePokemon("bulbasaur");

		assertEquals(45, testPokemon.getMaxHp());
		assertEquals(49, testPokemon.getAttack());
		assertEquals(49, testPokemon.getDefense());
		assertEquals(45, testPokemon.getSpeed());
		assertEquals(45, testPokemon.getMaxHp());

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
		Player player1 = playerService.savePlayer(new Player("Tony", "Bananananas", "vvv@hot.com"));
		Player player2 = playerService.savePlayer(new Player("Vytis", "Bananananas", "www@hot.com"));

		BattlePokemon pikachu = pokemonService.savePokemon(new BattlePokemon("pikachu"), player1);
		BattlePokemon wartortle = pokemonService.savePokemon(new BattlePokemon("wartortle"), player1);
		BattlePokemon butterfree = pokemonService.savePokemon(new BattlePokemon("butterfree"), player1);
		List<BattlePokemon> pokemons1 = new ArrayList<>(Arrays.asList(pikachu, wartortle, butterfree));

		BattlePokemon blastoise = pokemonService.savePokemon(new BattlePokemon("blastoise"), player2);
		BattlePokemon caterpie = pokemonService.savePokemon(new BattlePokemon("caterpie"), player2);
		BattlePokemon mew = pokemonService.savePokemon(new BattlePokemon("mew"), player2);
		List<BattlePokemon> pokemons2 = new ArrayList<>(Arrays.asList(blastoise, caterpie, mew));

//		List<BattlePokemon> pokemons1 = new ArrayList<>(Arrays.asList(pokemonService.savePokemon(new BattlePokemon("pikachu", attacker)) , pokemonService.savePokemon(new BattlePokemon("wartortle", attacker)), pokemonService.savePokemon(new BattlePokemon("butterfree", attacker))));
//		List<BattlePokemon> pokemons2 = new ArrayList<>(Arrays.asList(pokemonService.savePokemon(new BattlePokemon("blastoise", defender)) , pokemonService.savePokemon(new BattlePokemon("caterpie", defender)), pokemonService.savePokemon(new BattlePokemon("mew", defender))));

		player1.setStarters(pokemons1);
		player2.setStarters(pokemons2);

		boolean attackerStarts = battleService.doesAttackerStart(player1, player2);
		assertEquals(false, attackerStarts);

		player2.getStarters().remove(2);
		player2.getStarters().add(new BattlePokemon("charmander"));
		boolean attackStarts2 = battleService.doesAttackerStart(player1, player2);

		assertEquals(true, attackStarts2);

	}

	@Test
	public void findFastestWithTurnTest() {
		BattlePokemon blastoise = new BattlePokemon("blastoise");
		BattlePokemon caterpie = new BattlePokemon("caterpie");
		BattlePokemon mew = new BattlePokemon("mew");
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
		Player player1 = playerService.savePlayer(new Player("Tony", "Bananananas", "vvv@hot.com"));
		Player player2 = playerService.savePlayer(new Player("Vytis", "Bananananas", "www@hot.com"));

		BattlePokemon pikachu = pokemonService.savePokemon(new BattlePokemon("pikachu"), player1);
		BattlePokemon wartortle = pokemonService.savePokemon(new BattlePokemon("wartortle"), player1);
		BattlePokemon butterfree = pokemonService.savePokemon(new BattlePokemon("butterfree"), player1);
		List<BattlePokemon> pokemons1 = new ArrayList<>(Arrays.asList(pikachu, wartortle, butterfree));

		BattlePokemon blastoise = pokemonService.savePokemon(new BattlePokemon("blastoise"), player2);
		BattlePokemon caterpie = pokemonService.savePokemon(new BattlePokemon("caterpie"), player2);
		BattlePokemon mew = pokemonService.savePokemon(new BattlePokemon("mew"), player2);
		List<BattlePokemon> pokemons2 = new ArrayList<>(Arrays.asList(blastoise, caterpie, mew));

//		List<BattlePokemon> pokemons1 = new ArrayList<>(Arrays.asList(pokemonService.savePokemon(new BattlePokemon("pikachu", player1)) , pokemonService.savePokemon(new BattlePokemon("wartortle", player1)), pokemonService.savePokemon(new BattlePokemon("butterfree", player1))));
//		List<BattlePokemon> pokemons2 = new ArrayList<>(Arrays.asList(pokemonService.savePokemon(new BattlePokemon("blastoise", player2)) , pokemonService.savePokemon(new BattlePokemon("caterpie", player2)), pokemonService.savePokemon(new BattlePokemon("mew", player2))));

		player1.setStarters(pokemons1);
		player2.setStarters(pokemons2);

		List<String> battleLog = battleService.simulateBattle(player1, player2);

		Assertions.assertFalse(battleLog.isEmpty());

		for (int i = 0; i < battleLog.size(); i++) {
			System.out.println(battleLog.get(i));
		}

		battleService.createNewBattle(playerService.savePlayer(player1), playerService.savePlayer(player2), battleLog);

	}

	@Test
	public void healAllPokemontest() {
		Player player1 = playerService.savePlayer(new Player("Tony", "Bananananas", "vvv@hot.com"));
		Player player2 = playerService.savePlayer(new Player("Vytis", "Bananananas", "www@hot.com"));

		BattlePokemon pikachu = pokemonService.savePokemon(new BattlePokemon("pikachu"), player1);
		BattlePokemon wartortle = pokemonService.savePokemon(new BattlePokemon("wartortle"), player1);
		BattlePokemon butterfree = pokemonService.savePokemon(new BattlePokemon("butterfree"), player1);
		List<BattlePokemon> pokemons1 = new ArrayList<>(Arrays.asList(pikachu, wartortle, butterfree));

		BattlePokemon blastoise = pokemonService.savePokemon(new BattlePokemon("blastoise"), player2);
		BattlePokemon caterpie = pokemonService.savePokemon(new BattlePokemon("caterpie"), player2);
		BattlePokemon mew = pokemonService.savePokemon(new BattlePokemon("mew"), player2);
		List<BattlePokemon> pokemons2 = new ArrayList<>(Arrays.asList(blastoise, caterpie, mew));

//		List<BattlePokemon> pokemons1 = new ArrayList<>(Arrays.asList(pokemonService.savePokemon(new BattlePokemon("pikachu"), player1)) , pokemonService.savePokemon(new BattlePokemon("wartortle"), player1)), pokemonService.savePokemon(new BattlePokemon("butterfree", player1))));
//		List<BattlePokemon> pokemons2 = new ArrayList<>(Arrays.asList(pokemonService.savePokemon(new BattlePokemon("blastoise"), player2)) , pokemonService.savePokemon(new BattlePokemon("caterpie", player2)), pokemonService.savePokemon(new BattlePokemon("mew", player2))));

		player1.setStarters(pokemons1);
		player2.setStarters(pokemons2);

		pokemons2.get(2).setCurrentHp(50);
		assertEquals(50, pokemons2.get(2).getCurrentHp());
		assertEquals(100, pokemons2.get(2).getMaxHp());
		battleService.healAllPokemonAndResetDamageDone(player1, player2);
		assertEquals(100, pokemons2.get(2).getCurrentHp());
	}
	@Test
	public void damageMultiplierTest (){
		BattlePokemon charizard = new BattlePokemon("charizard");
		BattlePokemon caterpie = new BattlePokemon("caterpie");
		BattlePokemon blastoise = new BattlePokemon( "blastoise");
		BattlePokemon graveler = new BattlePokemon( "graveler");
		BattlePokemon snorlax = new BattlePokemon( "snorlax");

		assertEquals(2.0, battleService.damageMultiplier(charizard, caterpie));
		assertEquals(0.5, battleService.damageMultiplier(charizard, blastoise));
		assertEquals(0.5, battleService.damageMultiplier(charizard, graveler));
		assertEquals(1.0, battleService.damageMultiplier(charizard, snorlax));

	}
	@Test
	public void openPackTest(){
		Player player1 = playerService.savePlayer(new Player("Tony", "Bananananas", "vvv@hot.com"));
		Pack pack = packService.savePack(new Pack(3,100,player1,1));

		List<BattlePokemon> openedpack = packService.openPack(pack);
		for (int i = 0; i < openedpack.size(); i++) {
			System.out.println(openedpack.get(i).getName());
		}
		assertEquals(3, openedpack.size());


	}
}
