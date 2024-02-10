package ru.kata.spring.boot_security.demo.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "game")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Game {

    @Id
    private int id;
    boolean running;
    private byte random;

    private byte tryNumber;
    private byte gameRate;
    private byte totalRate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Game() {
    }

    public Game(int id, boolean running, byte random, byte tryNumber, byte gameRate, byte totalRate, User user) {
        this.id = id;
        this.running = running;
        this.random = random;
        this.tryNumber = tryNumber;
        this.gameRate = gameRate;
        this.totalRate = totalRate;
        this.user = user;
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

    public byte getRandom() {
        return random;
    }

    public void setRandom(byte random) {
        this.random = random;
    }

    public byte getTryNumber() {
        return tryNumber;
    }

    public void setTryNumber(byte tryNumber) {
        this.tryNumber = tryNumber;
    }

    public byte getGameRate() {
        return gameRate;
    }

    public void setGameRate(byte gameRate) {
        this.gameRate = gameRate;
    }

    public byte getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(byte totalRate) {
        this.totalRate = totalRate;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", running=" + running +
                ", random=" + random +
                ", tryNumber=" + tryNumber +
                ", gameRate=" + gameRate +
                ", totalRate=" + totalRate +
                '}';
    }
}
