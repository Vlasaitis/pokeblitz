package com.example.pokeblitz.Classes;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<BattlePokemon> allPokemon;
    @Transient
    private List<BattlePokemon> starters;
    @Transient
    private List<BattlePokemon> ko;

    public Player() {
    }

    public Player(int id, String username, String password, List<BattlePokemon> starters) {
        this.id = id;
        this.username = username;
//        this.allPokemon = allPokemon;
        this.starters = starters;
        this.ko = new ArrayList<>();
        this.password = password;
    }
    public Player(int id, String username, List<BattlePokemon> starters) {
        this.id = id;
        this.username = username;
        this.starters = starters;
        this.ko = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}


