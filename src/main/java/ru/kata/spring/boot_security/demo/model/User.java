package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, length = 21)
    @NotEmpty(message = "should not be empty")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "...you will need password to enter")
    private String password;

    @Column(name = "yob")
    @Min(value = 1923, message = "real age pls...")
    @Max(value = 2022, message = "real age pls...")
    private int yob;

    @Column(name = "country")
    @NotEmpty(message = "should not be empty")
    private String country;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User() {
    }

    public User(String username, String password, int yob, String country) {
        this.username = username;
        this.password = password;
        this.yob = yob;
        this.country = country;
    }

    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Role role) {
        roles.add(role);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public String toString() {
        StringBuffer passCut = new StringBuffer(password);
        passCut.setLength(8);

        return "User: " + "id= " + id + " | " + " name: " + username + " | " + " password: " + passCut + " | " + " year of bith - " + yob + " | " + " country - " + country + " | " + "access - " + roles.toString();
    }
}
