package com.example.pokeblitz.Classes;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "battle")
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Player winner;
    @ManyToOne
    private Player loser;

    @Transient
    private List<String> battleLog;


    public Battle() {
    }

    public Battle(Player winner, Player loser, List<String> battleLog) {
        this.winner = winner;
        this.loser = loser;
        this.battleLog = battleLog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getBattleLog() {
        return battleLog;
    }

    public void setBattleLog(List<String> battleLog) {
        this.battleLog = battleLog;
    }
    public Player getLoser() {
        return loser;
    }

    public void setLoser(Player loser) {
        this.loser = loser;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

}
