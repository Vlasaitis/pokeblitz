package com.example.pokeblitz.Classes;

import jakarta.persistence.*;

@Entity
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "winner_id")
//    private Player winner;
//    @ManyToOne
//    @JoinColumn(name = "loser_id")
//    private Player loser;


    public Battle() {
    }

//    public Battle(Player winner, Player loser) {
//        this.winner = winner;
//        this.loser = loser;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Player getLoser() {
//        return loser;
//    }
//
//    public void setLoser(Player loser) {
//        this.loser = loser;
//    }
//
//    public Player getWinner() {
//        return winner;
//    }
//
//    public void setWinner(Player winner) {
//        this.winner = winner;
//    }

}
