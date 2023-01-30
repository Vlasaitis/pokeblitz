package com.example.pokeblitz.Services;

//import com.example.pokeblitz.Classes.Battle;

import com.example.pokeblitz.Classes.Battle;
import com.example.pokeblitz.Classes.BattlePokemon;
import com.example.pokeblitz.Classes.Player;
import com.example.pokeblitz.Repositories.BattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BattleService {
    private Random random = new Random();
    @Autowired
    BattleRepository battleRepository;
    @Autowired
    PlayerService playerService;

    public void createNewBattle(Player winner, Player loser, List<String> battleLog) {
        battleRepository.save(new Battle(winner, loser, battleLog));
    }

    public List<String> simulateBattle(Player attacker, Player defender) {
        attacker.setBattleStarters(attacker.getStarters().returnStarters()); // temp transient property for memory
        defender.setBattleStarters(defender.getStarters().returnStarters());

        Random random = new Random();
        boolean gameNotOver = true;
        List<String> battleLog = new ArrayList<>();
        boolean attackerTurn = doesAttackerStart(attacker, defender);
        while (gameNotOver) {
            if (attackerTurn) {
                simulateAttack(attacker, defender, random, battleLog); // first pokemon argument attacks, second defends
                attackerTurn = false;
            } else {
                simulateAttack(defender, attacker, random, battleLog);
                attackerTurn = true;
            }
            gameNotOver = shouldGameContinue(attacker, defender);
        }
        addBattleSummaryToLogChangeEloGiveCoins(attacker, defender, battleLog);
        healAllPokemonAndResetDamageDone(attacker, defender);

        return battleLog;
    }


    public void addBattleSummaryToLogChangeEloGiveCoins(Player attacker, Player defender, List<String> battleLog) {
        battleLog.add("-------------------");
        if (attacker.getBattleStarters().isEmpty()) { // means defender won
            battleLog.add(defender.getUsername() + " won the battle and stole 10 ELO! Fight summary below:");
            battleLog.add("-------------------");
            adjustEloAndWinLoss(defender,attacker);
            defender.addCoins(100);
        } else {
            battleLog.add(attacker.getUsername() + " won the battle and stole 10 ELO! Fight summary:");
            battleLog.add("-------------------");
            adjustEloAndWinLoss(attacker, defender);
            attacker.addCoins(100);
        }
//        playerService.savePlayer(defender);
//        playerService.savePlayer(attacker);
        battleLog.add(defender.getUsername() + "'s Pokemon:");
        defender.getBattleStarters().stream().forEach(battlePokemon -> battleLog.add(String.format("%s (HP:%d/%d): Total damage: %d", battlePokemon.getName(), battlePokemon.getCurrentHp(), battlePokemon.getMaxHp(), battlePokemon.getDamageDone())));
        defender.getKo().stream().forEach(battlePokemon -> battleLog.add(String.format("%s (HP:%d/%d): Total damage: %d", battlePokemon.getName(), battlePokemon.getCurrentHp(), battlePokemon.getMaxHp(), battlePokemon.getDamageDone())));
        battleLog.add("-------------------");
        battleLog.add(attacker.getUsername() + "'s Pokemon:");
        attacker.getBattleStarters().stream().forEach(battlePokemon -> battleLog.add(String.format("%s (HP:%d/%d): Total damage: %d", battlePokemon.getName(), battlePokemon.getCurrentHp(), battlePokemon.getMaxHp(), battlePokemon.getDamageDone())));
        attacker.getKo().stream().forEach(battlePokemon -> battleLog.add(String.format("%s (HP:%d/%d): Total damage: %d", battlePokemon.getName(), battlePokemon.getCurrentHp(), battlePokemon.getMaxHp(), battlePokemon.getDamageDone())));
        battleLog.add("-------------------");
//        for (int i = 0; i < defender.getStarters().size(); i++) {
////            battleLog.add(String.format("%s's %s (HP:%d/%d): Total damage: %d", defender.getUsername(),defender.getStarters().get(i).getName(), defender.getStarters().get(i).getCurrentHp(), defender.getStarters().get(i).getMaxHp(), defender.getStarters().get(i).getDamageDone()));
//        }
    }

    private void adjustEloAndWinLoss(Player winner, Player loser) {
        winner.increaseElo(10);
        winner.wonAGame();

        loser.decreaseElo(10);
        loser.lostAGame();
    }

    public void healAllPokemonAndResetDamageDone(Player attacker, Player defender) {
        attacker.getStarters().returnStarters().stream().forEach(battlePokemon -> battlePokemon.setCurrentHp(battlePokemon.getMaxHp()));
        defender.getStarters().returnStarters().stream().forEach(battlePokemon -> battlePokemon.setCurrentHp(battlePokemon.getMaxHp()));

        attacker.getStarters().returnStarters().stream().forEach(battlePokemon -> battlePokemon.setDamageDone(0));
        defender.getStarters().returnStarters().stream().forEach(battlePokemon -> battlePokemon.setDamageDone(0));

        attacker.getKo().clear();
        defender.getKo().clear();

        attacker.getStarters().returnStarters().stream().forEach(battlePokemon -> battlePokemon.setHasTurn(true));
        defender.getStarters().returnStarters().stream().forEach(battlePokemon -> battlePokemon.setHasTurn(true));


        playerService.savePlayer(attacker);
        playerService.savePlayer(defender);
    }

    public boolean shouldGameContinue(Player attacker, Player defender) {
        if (attacker.getBattleStarters().isEmpty()) {
            //lägg tillbaka pokemon i starter
            return false;
        } else if (defender.getBattleStarters().isEmpty()) {
            //lägg tillbaka pokemon i starter
            return false;
        }
        return true;
    }

    public void simulateAttack(Player attacker, Player defender, Random random, List<String> battleLog) {
        BattlePokemon attackingPokemon = fastestWithTurn(attacker.getBattleStarters());
        int randomIndex = random.nextInt(defender.getBattleStarters().size());
        BattlePokemon offer = defender.getBattleStarters().get(randomIndex);
        dealAndLogDamage(attackingPokemon, offer, battleLog);

        if (offer.getCurrentHp() <= 0) {
            battleLog.add(offer.getName() + " has fainted.");
            defender.getKo().add(offer);
            defender.getBattleStarters().remove(offer);
        }

    }

    public void dealAndLogDamage (BattlePokemon attackingPokemon, BattlePokemon offer, List <String> battleLog){
        double damageMultiplier = damageMultiplier(attackingPokemon, offer);
        int critDamage = checkIfCrit();
        int totalDamage = (int) (damageMultiplier * critDamage * ((10 * (1 + ((double)attackingPokemon.getAttack() / 100))) * (1 - ((double)offer.getDefense() / 300))));
        offer.setCurrentHp(offer.getCurrentHp() - totalDamage);
        attackingPokemon.setDamageDone(attackingPokemon.getDamageDone() + totalDamage);
        if (critDamage == 2 && damageMultiplier == 2.0) {
            battleLog.add(String.format("%s attacks %s. The attack type is super effective & it's a critical hit! %s takes %d damage. %s's HP: %d/%d.", attackingPokemon.getName(), offer.getName(), offer.getName(), totalDamage, offer.getName(),offer.getCurrentHp(), offer.getMaxHp()));
        } else if (critDamage == 1 && damageMultiplier == 2.0) {
            battleLog.add(String.format("%s attacks %s. The attack type is super effective! %s takes %d damage. %s's HP: %d/%d.", attackingPokemon.getName(), offer.getName(), offer.getName(), totalDamage, offer.getName(),offer.getCurrentHp(), offer.getMaxHp()));
        } else if (critDamage == 2 && damageMultiplier == 1.0) {
            battleLog.add(String.format("%s attacks %s. The attack is a critical hit! %s takes %d damage. %s's HP: %d/%d.", attackingPokemon.getName(), offer.getName(), offer.getName(), totalDamage, offer.getName(),offer.getCurrentHp(), offer.getMaxHp()));
        } else if (critDamage == 2 && damageMultiplier == 0.5) {
            battleLog.add(String.format("%s attacks %s. The attack is not very effective... But it's a critical hit! %s takes %d damage. %s's HP: %d/%d.", attackingPokemon.getName(), offer.getName(), offer.getName(), totalDamage, offer.getName(),offer.getCurrentHp(), offer.getMaxHp()));
        } else if (critDamage == 1 && damageMultiplier == 0.5) {
            battleLog.add(String.format("%s attacks %s. The attack is not very effective... %s takes %d damage. %s's HP: %d/%d.", attackingPokemon.getName(), offer.getName(), offer.getName(), totalDamage, offer.getName(),offer.getCurrentHp(), offer.getMaxHp()));
        } else {
            battleLog.add(String.format("%s attacks %s. %s takes %d damage. %s's HP: %d/%d.", attackingPokemon.getName(), offer.getName(), offer.getName(), totalDamage, offer.getName(),offer.getCurrentHp(), offer.getMaxHp()));
        }
    }

    private int checkIfCrit () {
        if (random.nextDouble() <= 0.05) {
            return 2;
        }
        return 1;
    }

    public double damageMultiplier (BattlePokemon attacker, BattlePokemon defender){
        List<String> defenderTypes = defender.getTypes();
        if (attacker.getDoubleDamage().stream().anyMatch(defenderTypes::contains)) {
            return 2.0;
        } else if (attacker.getHalfDamage().stream().anyMatch(defenderTypes::contains)) {
            return 0.5;
        }
        return 1.0;
    }

    public BattlePokemon fastestWithTurn (List < BattlePokemon > starters) {
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

    public boolean doesAttackerStart (Player attacker, Player defender){
        int attackerHighestSpeed = attacker.getBattleStarters().get(0).getSpeed();
        int defenderHighestSpeed = defender.getBattleStarters().get(0).getSpeed();
        for (int i = 1; i <= 2; i++) {
            int currentAtkPokemonSpeed = attacker.getBattleStarters().get(i).getSpeed();
            int currentDefPokemonSpeed = defender.getBattleStarters().get(i).getSpeed();

            if (currentAtkPokemonSpeed > attackerHighestSpeed) {
                attackerHighestSpeed = currentAtkPokemonSpeed;

            }
            if (currentDefPokemonSpeed > defenderHighestSpeed) {
                defenderHighestSpeed = currentDefPokemonSpeed;

            }
        }
        return (attackerHighestSpeed >= defenderHighestSpeed);
    }

    public Player findRandomOpponent(Player attacker) {
        List<Player> allPlayers = playerService.getAllPlayers(); // all players
        List<Long> allPlayerIds = new ArrayList<>();
        allPlayers.forEach(player -> allPlayerIds.add(player.getId())); // loops through all players in DB, extracts their ID and adds to allPlayerIds

        // loops until it finds an opponent that isnt itself
        while (true) {
            int randomIndex = random.nextInt(allPlayerIds.size());
            Long randomId = allPlayerIds.get(randomIndex);
            if (!(randomId == attacker.getId())) {
                return playerService.findUserById(randomId);
            }
        }
    }
}
