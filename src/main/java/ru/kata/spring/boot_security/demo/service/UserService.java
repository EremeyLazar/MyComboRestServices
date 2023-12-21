package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
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

    public boolean isUserExists(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new Role(1));
        user.setRoles(new Role(3));
        userRepository.save(user);
    }

    @Transactional
    public void createAdmin(User admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRoles(new Role(2));
        userRepository.save(admin);
    }


    @Transactional
    public void createTheAdmin() {
        Role roleUser = new Role(1, "ROLE_USER");
        Role roleAdmin = new Role(2, "ROLE_ADMIN");
        Role roleReadonly = new Role(3, "ROLE_READONLY");

        roleRepository.saveAll(List.of(roleUser, roleAdmin, roleReadonly));

        User admin = new User("admin", "admin", 2000, "admin");
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRoles(roleAdmin);
        userRepository.save(admin);
    }

    @Transactional
    public void deleteUser(int id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("user with this ID does not exists");
        }
    }


    @Transactional
    public void update(User updatedUser, int id) {
        userRepository.findById(id).ifPresentOrElse(
                user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setPassword(updatedUser.getPassword());
                    user.setYob(updatedUser.getYob());
                    user.setCountry(updatedUser.getCountry());
                    userRepository.save(user);
                },
                () -> {
                    throw new IllegalArgumentException("User with this ID does not exist");
                }
        );
    }

    public User getOne(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with this ID does not exist"));
    }
}
