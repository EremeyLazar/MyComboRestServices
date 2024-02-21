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
    boolean running;
    private double totalRate;

    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @OneToOne(mappedBy = "game", fetch = FetchType.LAZY)
    private User user;


    public Game() {
    }

    public Game(int id) {
        this.id = id;
    }

    public Game(boolean running, double totalRate) {
        this.running = running;
        this.totalRate = totalRate;
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

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", running=" + running +
                ", totalRate=" + totalRate +
                '}';
    }
}
