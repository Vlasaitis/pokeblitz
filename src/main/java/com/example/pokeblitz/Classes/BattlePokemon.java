package com.example.pokeblitz.Classes;

import com.github.oscar0812.pokeapi.models.pokemon.Pokemon;
import com.github.oscar0812.pokeapi.models.pokemon.PokemonType;
import com.github.oscar0812.pokeapi.utils.Client;

import java.util.ArrayList;
import java.util.List;

public class BattlePokemon {
    private int id;
    private String name;
    private int hp;
    private int attack;
    private int defense;
    private int speed;
    private String image;
    private List<String> types;
    private List<String> doubleDamage;
    private List<String> halfDamage;
    // player class here


    public BattlePokemon() {
    }

    public BattlePokemon(int id, String name) {
        Pokemon poke = Client.getPokemonByName(name);
        this.id = id;
        this.name = name;
        this.hp = poke.getStats().get(0).getBaseStat();
        this.attack = poke.getStats().get(1).getBaseStat();
        this.defense = poke.getStats().get(2).getBaseStat();
        this.speed = poke.getStats().get(5).getBaseStat();
        this.image = poke.getSprites().getFrontDefault();
        this.types = setPokeTypes(poke);
        this.doubleDamage = setDblDmg(types);
        this.halfDamage = setHalfDmg(types);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
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
}


