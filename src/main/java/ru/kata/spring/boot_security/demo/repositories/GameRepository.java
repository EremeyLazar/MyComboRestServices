package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Game;
import ru.kata.spring.boot_security.demo.model.User;


@Repository
public interface GameRepository extends JpaRepository <Game, Integer> {

}
