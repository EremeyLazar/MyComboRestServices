package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.exception_handling.NoSuchUserException;
import ru.kata.spring.boot_security.demo.model.Game;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }


    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoles().size() == 0) {
            user.setRoles(Collections.singleton(new Role(1)));
        }
        user.setGame(new Game(false, 0d));
        userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("This user does not exist anyway in our DB");
        }
    }

    public void update(User updatedUser, Integer id) {
        userRepository.findById(id).ifPresentOrElse(
                user -> {
                    if (!Objects.equals(updatedUser.getPassword(), user.getPassword())) {
                        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                    }
                    user.setUsername(updatedUser.getUsername());
                    user.setCountry(updatedUser.getCountry());
                    userRepository.save(user);
                },
                () -> {
                    throw new IllegalArgumentException("User with this ID does not exist");
                }
        );
    }



}
