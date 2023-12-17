package ru.kata.spring.boot_security.demo.model;

import javax.persistence.*;



@Entity
@Table (name = "users")
public class User {
    // id, name, password, yob, country, roll

    @Id
    @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, length = 21)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "yob")
    private int yob;

    @Column(name = "country")
    private String country;

    @Column(name = "role")
    private String role;



    public User() {
    }

    public User(String username, String password, int yob, String country) {
        this.username = username;
        this.password = password;
        this.yob = yob;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getYob() {
        return yob;
    }

    public void setYob(int yob) {
        this.yob = yob;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        StringBuffer pass = new StringBuffer(password);
        pass.setLength(8);

        return "User: " + "id= " + id + " | " +
                " name: " + username + " | " +
                " password: " + pass + " | " +
                " year of bith - " + yob + " | " +
                " country - " + country + " | " + " role - " + role ;
    }

    public void setRole(String roles) {
        this.role = roles;
    }

    public String getRole() {
        return role;
    }
}
