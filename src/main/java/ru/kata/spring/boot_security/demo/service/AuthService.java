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
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthService implements UserDetailsService {

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

    @Transactional(readOnly = true)
    public boolean isUserExists(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Role getRoleById(int roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }


    public void createUser(User user, List<Integer> roleIds) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (roleIds != null) {
            Set<Role> roles = roleIds.stream()
                    .map(roleId -> getRoleById(roleId))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return (User) authentication.getPrincipal();
    }


    public void deleteUser(int id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("user does not exists anyway");
        }
    }


    public void update(User updatedUser, int id) {
        userRepository.findById(id).ifPresentOrElse(
                user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setCountry(updatedUser.getCountry());
                    userRepository.save(user);
                },
                () -> {
                    throw new IllegalArgumentException("User with this ID does not exist");
                }
        );
    }

    @Transactional(readOnly = true)
    public User getOne(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with this ID does not exist"));
    }
}
