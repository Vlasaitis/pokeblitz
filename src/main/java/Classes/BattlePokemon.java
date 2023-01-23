package Classes;

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

    public BattlePokemon(int id, String name, int hp, int attack, int defense, int speed, String image, List<String> types, List<String> doubleDamage, List<String> halfDamage) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.image = image;
        this.types = types;
        this.doubleDamage = doubleDamage;
        this.halfDamage = halfDamage;
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


