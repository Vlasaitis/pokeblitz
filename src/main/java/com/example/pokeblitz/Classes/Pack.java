package com.example.pokeblitz.Classes;

import jakarta.persistence.*;

@Entity
@Table(name = "packs")
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    private int pokemonAmount;
    private int price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;
    private boolean used;
    private int tier;

    private String packImage;

    public Pack() {
    }

    public Pack(int pokemonAmount, int price, int tier) {
        this.name = setPackName(tier);
        this.pokemonAmount = pokemonAmount;
        this.price = price;
        this.used = false;
        this.player = new Player();
        this.tier = tier;
        this.packImage = generatePackImage(tier);
    }

    public Pack(int pokemonAmount, int price, Player player, int tier) {
        this.name = setPackName(tier);
        this.pokemonAmount = pokemonAmount;
        this.price = price;
        this.player = player;
        this.used = false;
        this.tier = tier;
        this.packImage = generatePackImage(tier);
    }

    private String generatePackImage(int tier) {
        if (tier == 4){
            return "epic.png";
        } else if (tier == 3) {
            return "rare.png";
        } else if (tier == 2) {
            return "uncommon.png";
        } else {
            return "common.png";
        }
    }

    public String getPackImage() {
        return packImage;
    }

    public void setPackImage(String packImage) {
        this.packImage = packImage;
    }

    private String setPackName(int tier) {
        if (tier == 4){
            return "Epic Pack";
        } else if (tier == 3) {
            return "Rare Pack";
        } else if (tier == 2) {
            return "Uncommon Pack";
        } else {
            return "Common Pack";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Pack setUsed() {
        this.used = true;
        return this;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }
}
