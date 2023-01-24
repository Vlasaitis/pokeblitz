package com.example.pokeblitz.Services;

import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Player;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BattleService {
    public List<String> simulateBattle(Player attacker, Player defender) {
        Random random = new Random();
        List<String> battleLog = new ArrayList<>();
//        List<BattlePokemon> attackerPokemon = attacker.getStarters();
//        List<BattlePokemon> defenderPokemon = defender.getStarters();
        boolean attackerTurn = doesAttackerStart(attacker, defender);
        while (true) {
            if(attackerTurn) {
                battleLog.add(simulateAttack(attacker, defender)); // first pokemon argument attacks, second defends
            } else {
                battleLog.add(simulateAttack(defender, attacker));
            }
        }

        return battleLog;
    }
    private String simulateAttack(Player attacker, Player defender) {
        BattlePokemon attackingPokemon = fastestWithoutTurnConsumed(attacker.getStarters());
        return "hi";
    }

    private BattlePokemon fastestWithoutTurnConsumed(List<BattlePokemon> starters) {
        int HighestSpeed = starters.get(0).getSpeed();
        for (int i = 0; i < starters.size(); i++) {
            int currentSpeed = starters.get(i).getSpeed();
            if(!starters.get(i).getTurnConsumed() && currentSpeed > HighestSpeed){
                return starters.get(i);
            }
        }

        return null;
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
