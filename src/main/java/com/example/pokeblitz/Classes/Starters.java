package com.example.pokeblitz.Classes;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Starters {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;
    @ManyToOne
    @JoinColumn(name = "battle_pokemon1_id")
    private BattlePokemon battlePokemon1;
    @ManyToOne
    @JoinColumn(name = "battle_pokemon2_id")
    private BattlePokemon battlePokemon2;
    @ManyToOne
    @JoinColumn(name = "battle_pokemon3_id")
    private BattlePokemon battlePokemon3;

    public Starters(Player player) {
        this.player = player;
    }

    public Starters(Player player, BattlePokemon battlePokemon1, BattlePokemon battlePokemon2, BattlePokemon battlePokemon3) {
        this.player = player;
        this.battlePokemon1 = battlePokemon1;
        this.battlePokemon2 = battlePokemon2;
        this.battlePokemon3 = battlePokemon3;
    }
    public Starters(Player player, List<BattlePokemon> pokemon) {
        this.player = player;
        this.battlePokemon1 = pokemon.get(0);
        this.battlePokemon2 = pokemon.get(1);
        this.battlePokemon3 = pokemon.get(2);
    }

    public void setNewStarters(BattlePokemon poke1, BattlePokemon poke2, BattlePokemon poke3) {
        this.battlePokemon1 = poke1;
        this.battlePokemon2 = poke2;
        this.battlePokemon3 = poke3;
    }

    public BattlePokemon getBattlePokemon1() {
        return battlePokemon1;
    }

    public void setBattlePokemon1(BattlePokemon battlePokemon1) {
        this.battlePokemon1 = battlePokemon1;
    }

    public List<BattlePokemon> returnStarters() {
        List<BattlePokemon> pokemon = new ArrayList<>();
        pokemon.add(battlePokemon1);
        pokemon.add(battlePokemon2);
        pokemon.add(battlePokemon3);
        return pokemon;
    }

    public BattlePokemon getBattlePokemon2() {
        return battlePokemon2;
    }

    public void setBattlePokemon2(BattlePokemon battlePokemon2) {
        this.battlePokemon2 = battlePokemon2;
    }

    public BattlePokemon getBattlePokemon3() {
        return battlePokemon3;
    }

    public void setBattlePokemon3(BattlePokemon battlePokemon3) {
        this.battlePokemon3 = battlePokemon3;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Starters() {
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
