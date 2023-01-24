package com.example.pokeblitz.Services;

import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BattleService {
    public List<String> simulateBattle(Player attacker, Player defender) {
        Random random = new Random();
        List<String> battleLog = new ArrayList<>();
        boolean attackerTurn = doesAttackerStart(attacker, defender);
        while (true) {
            if(attackerTurn) {
                battleLog.add(simulateAttack(attacker, defender, random, battleLog)); // first pokemon argument attacks, second defends
            } else {
                battleLog.add(simulateAttack(defender, attacker, random, battleLog));
            }
        }

//        return
    }
    public String simulateAttack(Player attacker, Player defender, Random random, List<String> battleLog) {
        BattlePokemon attackingPokemon = fastestWithTurn(attacker.getStarters());
        int randomIndex = random.nextInt(defender.getStarters().size());
        BattlePokemon offer = defender.getStarters().get(randomIndex);
        offer.setHp(offer.getHp()-10);
        battleLog.add(attackingPokemon.getName() + " attacks " + offer.getName() + " for 10 damage"); /// kommer ändra damage systemet här, kommer vara variabel

        return "hi";
    }

    public BattlePokemon fastestWithTurn(List<BattlePokemon> starters) {
        List<BattlePokemon> unconsumedTurns = new ArrayList<>();
        for (int i = 0; i < starters.size(); i++) {
            if (starters.get(i).hasTurn()) {
                unconsumedTurns.add(starters.get(i));
            }
        }
        if (unconsumedTurns.isEmpty()) {
            starters.stream().forEach(battlePokemon -> battlePokemon.setHasTurn(true));
            starters.stream().forEach(battlePokemon -> unconsumedTurns.add(battlePokemon));
        }

        BattlePokemon fastestWithTurn = unconsumedTurns.get(0);
        for (int i = 0; i < unconsumedTurns.size(); i++) {
            if (unconsumedTurns.get(i).getSpeed() > fastestWithTurn.getSpeed()) {
                fastestWithTurn = unconsumedTurns.get(i);
            }
        }
        fastestWithTurn.setHasTurn(false);

        return fastestWithTurn;
    }

    public boolean doesAttackerStart(Player attacker, Player defender) {
        int attackerHighestSpeed = attacker.getStarters().get(0).getSpeed();
        int defenderHighestSpeed = defender.getStarters().get(0).getSpeed();
        for (int i = 1; i <=2; i++) {
            int currentAtkPokemonSpeed = attacker.getStarters().get(i).getSpeed();
            int currentDefPokemonSpeed = defender.getStarters().get(i).getSpeed();

            if (currentAtkPokemonSpeed > attackerHighestSpeed) {
                attackerHighestSpeed = currentAtkPokemonSpeed;

            }
            if (currentDefPokemonSpeed > defenderHighestSpeed) {
                defenderHighestSpeed = currentDefPokemonSpeed;

            }
        }
        return (attackerHighestSpeed >= defenderHighestSpeed);
    }
}
