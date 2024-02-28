package ru.kata.spring.boot_security.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean running;
    private double totalRate;

    private byte level;

    private int games;


    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @OneToOne(mappedBy = "game", fetch = FetchType.LAZY)
    private User user;


    public Game() {
    }

    public Game(int id) {
        this.id = id;
    }

    public Game(boolean running, double totalRate, byte level, int games) {
        this.running = running;
        this.totalRate = totalRate;
        this.level = level;
        this.games = games;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public double getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(double totalRate) {
        this.totalRate = totalRate;
    }

    public byte getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", running=" + running +
                ", totalRate=" + totalRate +
                ", level=" + level +
                ", games=" + games +
                '}';
    }
}
