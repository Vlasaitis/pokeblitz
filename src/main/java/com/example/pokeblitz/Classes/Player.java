package com.example.pokeblitz.Classes;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotEmpty(message = "Empty Username")
    @Size(max = 15, message = "Username can max be 20 characters")
    private String username;
    @Column
    @NotEmpty(message = "Empty Password")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    @Column
//    @Email
//    @NotEmpty
    private String email;
    @Column
    private int elo = 1000;
    @Column
    private int coins = 4000;

    @Column
    private int wins = 0;
    @Column
    private int losses = 0;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    private List<BattlePokemon> allPokemon = new ArrayList<>();
    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    private Starters starters;

    @Transient
    private List<BattlePokemon> battleStarters = new ArrayList<>();
    @Transient
    private List<BattlePokemon> ko = new ArrayList<>();

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    private List<Pack> packs = new ArrayList<>();

    @OneToMany(mappedBy = "winner", cascade = CascadeType.ALL)
    private List<Battle> winningBattleHistory = new ArrayList<>();

    @OneToMany(mappedBy = "loser", cascade = CascadeType.ALL)
    private List<Battle> losingBattleHistory = new ArrayList<>();

    @Transient
    private List<Battle> fullBattleHistory = new ArrayList<>();


    public Player() {
    }

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.email = "defaultemail";

    }


    public Player(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;

    }

    public List<BattlePokemon> getBattleStarters() {
        return battleStarters;
    }

    public void setBattleStarters(List<BattlePokemon> battleStarters) {
        this.battleStarters = battleStarters;
    }

    public List<Battle> getWinningBattleHistory() {
        return winningBattleHistory;
    }

    public void setWinningBattleHistory(List<Battle> winningBattleHistory) {
        this.winningBattleHistory = winningBattleHistory;
    }

    public List<Battle> getLosingBattleHistory() {
        return losingBattleHistory;
    }

    public void setLosingBattleHistory(List<Battle> losingBattleHistory) {
        this.losingBattleHistory = losingBattleHistory;
    }

    public Player addPurchasedPack(Pack e) { // adds a purchased pack and returns the player object
        this.packs.add(e);
        return this;
    }
    public Pack findPackInBackPack(Long packId) { // adds a purchased pack and returns the player object
        for (int i = 0; i < this.packs.size(); i++) {
            if (this.packs.get(i).getId() == packId) {
                this.packs.get(i).setUsed();
                return this.packs.get(i);
            }
        }
        return new Pack();
    }
    public void deductCoins(int price) {
        this.coins -= price;
    }
    public void addCoins(int price) {
        this.coins += price;
    }

    public List<Battle> getFullBattleHistory() {
        return fullBattleHistory;
    }

    public void setFullBattleHistory(List<Battle> fullBattleHistory) {
        this.fullBattleHistory = fullBattleHistory;
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

    public Starters getStarters() {
        return starters;
    }

    public void setStarters(Starters starters) {
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

    public void increaseElo(int increaseBy) {
        this.elo += increaseBy;
    }
    public void decreaseElo(int decreaseBy) {
        this.elo -= decreaseBy;
    }
    public void wonAGame() {
        this.wins += 1;
    }
    public void lostAGame() {
        this.losses += 1;
    }


}




