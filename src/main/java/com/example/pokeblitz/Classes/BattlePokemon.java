package com.example.pokeblitz.Classes;

import com.github.oscar0812.pokeapi.models.pokemon.Pokemon;
import com.github.oscar0812.pokeapi.models.pokemon.PokemonType;
import com.github.oscar0812.pokeapi.utils.Client;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "battle_pokemon")
public class BattlePokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int maxHp;
    private int currentHp;
    private int attack;
    private int defense;
    private int speed;
    private int powerLevel;
    private String image;
    private List<String> types;
    private List<String> doubleDamage;
    private List<String> halfDamage;
    private Boolean hasTurn = true;
    private int damageDone = 0;
    private int pokemonNumber;
    public int level = 1;
    public int exp = 0;

    private boolean isTank;

    private int price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        if(exp >= 100){
            this.exp = exp - 100;
        } else
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
            if(level >= 100){
                this.level = 100;
            } else
                this.level = level;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    // player class here

    public BattlePokemon() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BattlePokemon that = (BattlePokemon) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public BattlePokemon(String name, Player player) {
        Pokemon poke = Client.getPokemonByName(name);
        this.name = poke.getName();
        this.maxHp = poke.getStats().get(0).getBaseStat();
        this.currentHp = maxHp;
        this.attack = poke.getStats().get(1).getBaseStat();
        this.defense = poke.getStats().get(2).getBaseStat();
        this.speed = poke.getStats().get(5).getBaseStat();
        this.powerLevel = maxHp + attack + defense + speed;
        this.image = poke.getSprites().getFrontDefault();
        this.types = setPokeTypes(poke);
        this.doubleDamage = setDblDmg(types);
        this.halfDamage = setHalfDmg(types);
        this.hasTurn = true;
        this.damageDone = 0;
        this.player = player;
        this.pokemonNumber = poke.getId();
        this.price = this.powerLevel/2;
        this.isTank = false;
    }

    public BattlePokemon(String name) { //// alt constructor. create a pokemon without having to create a player object. Mostly for tests. Don't have to create player obj to run test.
        Pokemon poke = Client.getPokemonByName(name);
        this.name = poke.getName();
        this.maxHp = poke.getStats().get(0).getBaseStat();
        this.currentHp = maxHp;
        this.attack = poke.getStats().get(1).getBaseStat();
        this.defense = poke.getStats().get(2).getBaseStat();
        this.speed = poke.getStats().get(5).getBaseStat();
        this.powerLevel = maxHp + attack + defense + speed;
        this.image = poke.getSprites().getFrontDefault();
        this.types = setPokeTypes(poke);
        this.doubleDamage = setDblDmg(types);
        this.halfDamage = setHalfDmg(types);
        this.hasTurn = true;
        this.damageDone = 0;
        this.player = new Player();
        this.pokemonNumber = poke.getId();
        this.price = this.powerLevel/2;
        this.isTank = false;
    }

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }

    private List<String> setHalfDmg(List<String> types) {
        List<String> halfDamage = new ArrayList<>();
        for (int i = 0; i < types.size(); i++) {
            String type = types.get(i);
            Client.getTypeByName(type).getDamageRelations().getHalfDamageTo().stream().forEach(type1 -> halfDamage.add(type1.getName()));
        }
        return halfDamage;
    }
    private List<String> setDblDmg(List<String> types) {
        List<String> doubleDamage = new ArrayList<>();
        for (int i = 0; i < types.size(); i++) {
            String type = types.get(i);
            Client.getTypeByName(type).getDamageRelations().getDoubleDamageTo().stream().forEach(type1 -> doubleDamage.add(type1.getName()));
        }
        return doubleDamage;
    }
    private List<String> setPokeTypes(Pokemon poke) {
        List<PokemonType> types = poke.getTypes();
        List<String> returnTypes = new ArrayList<>();
        types.stream().forEach(pokemonType -> returnTypes.add(pokemonType.getType().getName()));
        return returnTypes;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int baseHp) {
        this.maxHp = baseHp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getDoubleDamage() {
        return doubleDamage;
    }

    public void setDoubleDamage(List<String> doubleDamage) {
        this.doubleDamage = doubleDamage;
    }

    public List<String> getHalfDamage() {
        return halfDamage;
    }

    public void setHalfDamage(List<String> halfDamage) {
        this.halfDamage = halfDamage;
    }

    public Boolean hasTurn() {
        return hasTurn;
    }

    public void setHasTurn(Boolean hasTurn) {
        this.hasTurn = hasTurn;
    }
    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
        if (this.currentHp < 0) {
            this.currentHp = 0;
        }
    }
    public int getDamageDone() {
        return damageDone;
    }

    public void setDamageDone(int damageDone) {
        this.damageDone = damageDone;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public int getPokemonNumber() {
        return pokemonNumber;
    }

    public void setPokemonNumber(int pokemonNumber) {
        this.pokemonNumber = pokemonNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isTank() {
        return isTank;
    }

    public void setTank(boolean tank) {
        isTank = tank;
    }
}


