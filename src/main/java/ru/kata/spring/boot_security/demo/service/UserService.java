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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

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

    @Transactional
    public void createTheUser() {
        em.createNativeQuery("INSERT INTO role (id, name) VALUES (1, 'ROLE_USER')").executeUpdate();
        em.createNativeQuery("INSERT INTO role (id, name) VALUES (2, 'ROLE_ADMIN')").executeUpdate();
        em.createNativeQuery("INSERT INTO role (id, name) VALUES (3, 'ROLE_READONLY')").executeUpdate();
        User user = new User("Ashas", "1qa", 2000, "Russia");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new Role(1L));
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new Role(1L));
        user.setRoles(new Role(3L));
        userRepository.save(user);
    }

    @Transactional
    public void createAdmin() {
        User admin = new User("admin", "admin", 2000, "admin");
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRoles(new Role(2L));
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
        User needsUpdate = em.find(User.class, id);
        needsUpdate.setUsername(updatedUser.getUsername());
        needsUpdate.setPassword(updatedUser.getPassword());
        needsUpdate.setYob(updatedUser.getYob());
        needsUpdate.setCountry(updatedUser.getCountry());
        em.persist(needsUpdate);
    }

    public User getOne(int id) {
        if (userRepository.findById(id).isPresent()) {
            return em.find(User.class, id);
        } else {
            throw new IllegalArgumentException("user with this ID does not exists");
        }
    }


}
