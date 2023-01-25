package com.example.pokeblitz.Classes;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private int elo;
    @Column
    private int coins;

    @Column
    private int wins;
    @Column
    private int losses;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<BattlePokemon> allPokemon;
    @Transient
    private List<BattlePokemon> starters;
    @Transient
    private List<BattlePokemon> ko;
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Pack> packs;

//    @OneToMany(mappedBy = "winner", cascade = CascadeType.ALL)
//    private List<Battle> winningBattleHistory;
//
//    @OneToMany(mappedBy = "loser", cascade = CascadeType.ALL)
//    private List<Battle> losingBattleHistory;

    public Player() {
    }

    public Player(String username, String password, String email, List<Pack> packs) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.elo = 1000;
        this.coins = 500;
        this.wins = 0;
        this.losses = 0;
        this.allPokemon = new ArrayList<>();
        this.starters = new ArrayList<>();
        this.ko = new ArrayList<>();
        this.packs = giveStarterPack();
//        this.winningBattleHistory = new ArrayList<>();
//        this.losingBattleHistory = new ArrayList<>();
    }

//    public List<Battle> getWinningBattleHistory() {
//        return winningBattleHistory;
//    }
//
//    public void setWinningBattleHistory(List<Battle> winningBattleHistory) {
//        this.winningBattleHistory = winningBattleHistory;
//    }
//
//    public List<Battle> getLosingBattleHistory() {
//        return losingBattleHistory;
//    }
//
//    public void setLosingBattleHistory(List<Battle> losingBattleHistory) {
//        this.losingBattleHistory = losingBattleHistory;
//    }

    private List<Pack> giveStarterPack() {
        List<Pack> starterPack = new ArrayList<>(); /// utveckla
        return starterPack;
    }

    public Player(Long id, String username, String password, List<BattlePokemon> starters) {
        this.id = id;
        this.username = username;
//        this.allPokemon = allPokemon;
        this.starters = starters;
        this.ko = new ArrayList<>();
        this.password = password;
    }
    public Player(Long id, String username, List<BattlePokemon> starters) {
        this.id = id;
        this.username = username;
        this.starters = starters;
        this.ko = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<BattlePokemon> getStarters() {
        return starters;
    }

    public void setStarters(List<BattlePokemon> starters) {
        this.starters = starters;
    }

    public List<BattlePokemon> getKo() {
        return ko;
    }

    public void setKo(List<BattlePokemon> ko) {
        this.ko = ko;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }



    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public List<Pack> getPacks() {
        return packs;
    }

    public void setPacks(List<Pack> packs) {
        this.packs = packs;
    }

    public List<BattlePokemon> getAllPokemon() {
        return allPokemon;
    }

    public void setAllPokemon(List<BattlePokemon> allPokemon) {
        this.allPokemon = allPokemon;
    }
}


