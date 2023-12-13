package ru.kata.spring.boot_security.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository <User, Integer> {
    Optional <User> findByUsername(String username);

}
