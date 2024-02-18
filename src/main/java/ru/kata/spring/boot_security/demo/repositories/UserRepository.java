package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;


@Repository
public interface UserRepository extends JpaRepository <User, Integer> {
//    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = :username")
    @Query("SELECT u FROM User u JOIN FETCH u.roles LEFT JOIN FETCH u.game WHERE u.username = :username")
    User findByUsername(String username);
}
