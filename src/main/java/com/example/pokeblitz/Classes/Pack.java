package com.example.pokeblitz.Classes;

import jakarta.persistence.*;

@Entity
@Table(name = "pack")
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int pokemonAmount;
    private int price;
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
    private boolean used;
    private int tier;

    public Pack() {
    }

    public Pack(int pokemonAmount, int price, Player player, int tier) {
        this.pokemonAmount = pokemonAmount;
        this.price = price;
        this.player = player;
        this.used = false;
        this.tier = tier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPokemonAmount() {
        return pokemonAmount;
    }

    public void setPokemonAmount(int pokemonAmount) {
        this.pokemonAmount = pokemonAmount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }
}
