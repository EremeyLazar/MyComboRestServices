package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.controllers.GameController;
import ru.kata.spring.boot_security.demo.exception_handling.NoSuchUserException;
import ru.kata.spring.boot_security.demo.model.Game;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.GameRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private ArrayList<String> allWords = new ArrayList<>();


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User getCurrentUser() {
        System.out.println("GET CURRENT USER START");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        User user = (User) authentication.getPrincipal();
        System.out.println("CURRENT USER IS - " + user.toString());
        return user;
    }


    public User getOne(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new NoSuchUserException("there is no USER with ID = " + id + " found in DB");
        }
        return user;
    }


    //GAME SECTION // GAME SECTION // GAME SECTION // GAME SECTION // GAME SECTION // GAME SECTION // GAME SECTION //


    public void init(byte answer) {
        Random randomGenerator = new Random();
        User currentUser = getCurrentUser();
        Game game = currentUser.getGame();

        if (currentUser.getGame() == null) {
            currentUser.setGame(new Game());
            System.out.println("if inside");
            game.setRunning(true);
            game.setRandom(randomGenerator.nextInt(100));
            game.setTryNumber((byte) 1);
            game.setGameRate((byte) 0);
            sayWord(currentUser.getUsername() + ", число загадано! Начинаем...");
        }
        engine(game, answer);
    }

    public void engine(Game game, byte answer) {

        try {
            if (answer < game.getRandom()) {
                sayWord("Не угадали, ВАШЕ число МЕНЬШЕ загаданного!!");
            } else if (answer > game.getRandom()) {
                sayWord("Не угадали, ВАШЕ число БОЛЬШЕ загаданного!!");
            } else if (answer == game.getRandom()) {
            sayWord("УСПЕХ!!!");
            }
        } catch (java.util.InputMismatchException exception) {
            sayWord("Загадано число, а не слово... Вай меееее....");
        }
        game.setTryNumber((byte) (game.getTryNumber() + 1));
        sayWord("Попытка № " + game.getTryNumber());

    }



    public void sayWord (String word) {
        allWords.add(word);
    }

    public List <String> passWord () {
        List<String> reversedList = new ArrayList<>();
        for (int i = allWords.size() - 1; i >= 0; i--) {
            reversedList.add(allWords.get(i));
        }
        return reversedList.stream().limit(8).collect(Collectors.toList());
    }

}
