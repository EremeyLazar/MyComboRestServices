package ru.kata.spring.boot_security.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table (name = "users")
public class User {
    // id, name, password, yob, country, roll

    @Id
    @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "name should be visible")
    @Size(min = 2, max = 21, message = "name should be real")
    @Column(name = "username", nullable = false, length = 21)
    private String username;

    @NotEmpty(message = "name should be visible")
    @Size(min = 2, max = 21, message = "name should be real")
    @Column(name = "password", nullable = false, length = 21)
    private String password;

    @Min(value = 1938, message = "please manifest real age")
    @Max(value = 2022, message = "please manifest real age")
    @Column(name = "yob")
    private int yob;

    @NotEmpty(message = "Country should be visible")
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
        setRole("ROLE_USER");
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User: " + "id= " + id + " | " +
                " name: " + username + " | " +
                " password: " + password + " | " +
                " year of bith - " + yob + " | " +
                " country - " + country + " | " +
                " access - " + role + " | ";
    }
}
