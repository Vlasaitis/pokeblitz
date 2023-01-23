package com.example.pokeblitz.Classes;

import org.apache.tomcat.util.digester.ArrayStack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;
    private String username;
    private List<BattlePokemon> starters;
    private List<BattlePokemon> ko;


    public Player() {
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
}


