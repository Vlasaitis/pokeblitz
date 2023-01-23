package com.example.pokeblitz.Services;

import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Player;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BattleService {
    public List<String> simulateBattle(Player attacker, Player defender) {
        Random random = new Random();
        List<String> battleLog = new ArrayStack<>();
        List<BattlePokemon> attackerPokemon = attacker.getStarters();
        List<BattlePokemon> defenderPokemon = defender.getStarters();
        boolean attackerTurn = doesAttackerStart(attacker, defender);


//        while (true) {
//            if(attackerTurn) {
//                battleLog.add(simulateAttack(attackerPokemon, defenderPokemon));
//            }
//        }
//
        return battleLog;
    }

    private String simulateAttack(List<BattlePokemon> attackerPokemon, List<BattlePokemon> defenderPokemon) {
        return "hi";
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
