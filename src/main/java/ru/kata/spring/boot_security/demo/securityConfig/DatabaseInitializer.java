//package ru.kata.spring.boot_security.demo.securityConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import ru.kata.spring.boot_security.demo.model.Role;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
//import ru.kata.spring.boot_security.demo.repositories.UserRepository;
//
//import javax.annotation.PostConstruct;
//import java.util.Set;
//
//
//@Component
//public class DatabaseInitializer {
//    private final RoleRepository roleRepository;
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public DatabaseInitializer(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.roleRepository = roleRepository;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @PostConstruct
//    public void initializeDatabase() {
//        Role roleUser = roleRepository.findByName("ROLE_USER")
//                .orElseGet(() -> roleRepository.save(new Role(1, "ROLE_USER")));
//
//        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN")
//                .orElseGet(() -> roleRepository.save(new Role(2, "ROLE_ADMIN")));
//
//        if (!userRepository.existsByUsername("admin")) {
//            User admin = new User("admin", "admin", 2000, "admin");
//            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
//            admin.setRoles(Set.of(roleAdmin, roleUser));
//
//            userRepository.save(admin);
//        }
//    }
//}
