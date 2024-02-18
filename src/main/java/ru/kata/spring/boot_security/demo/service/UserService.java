package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.controllers.AuthRestController;
import ru.kata.spring.boot_security.demo.exception_handling.NoSuchUserException;
import ru.kata.spring.boot_security.demo.model.Game;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;


    private ArrayList<String> allWords = new ArrayList<>();

    private int random;
    private byte tryNumber;
    private byte gameRate;
    private final int MAX_SIZE = 12;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        User user = (User) authentication.getPrincipal();
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


    public void init(int answer) {
        Random randomGenerator = new Random();
        User currentUser = getCurrentUser();

        if (!currentUser.getGame().isRunning()) {
            allWords.clear();
            random = randomGenerator.nextInt(100);
            System.out.println(random);
            tryNumber = 0;
            gameRate = 0;
            currentUser.getGame().setRunning(true);
        }
        tryNumber++;
        try {
            if (answer < random) {
                sayWord("Число " + answer + " МЕНЬШЕ загаданного!!");
                sayWord("Попытка № " + tryNumber);
            } else if (answer > random) {
                sayWord("Число " + answer + " БОЛЬШЕ загаданного!!");
                sayWord("Попытка № " + tryNumber);
            } else if ( answer == random){ finish(currentUser);
            }
        } catch (java.util.InputMismatchException exception) {
            sayWord("Загадано число, а не слово... Вай меееее....");
        }
    }

    public void finish (User user) {

        Game game = user.getGame();

            game.setTotalRate((game.getTotalRate() + gameRate)/2);
            sayWord("Ваш общий рейтинг составляет: " + game.getTotalRate() + " баллов!!!");

        if (tryNumber == 1) {
            sayWord("Поздравляю!");
            sayWord("Вы получаете 50 (!!!) очков!!!");
            sayWord("ВАУ!!! 777!!! БИНГО!!! Вы угадали с первой попытки!!!");
            gameRate = 50;
        } else if (tryNumber == 2) {
            sayWord("Поздравляю!");
            sayWord("Вы получаете 30 (!!!) очков!!!");
            sayWord("Офигеть!!! Вы угадали со второй попытки!!!");
            gameRate = 30;
        } else if (tryNumber == 3) {
            sayWord("Поздравляю!");
            sayWord("Вы получаете 20 (!!!) очков!!!");
            sayWord("Офигеть!!! Вы угадали с третьей попытки!!!");
            gameRate = 20;
        } else if (tryNumber < 100) {
            sayWord("Не останавливайтесь!");
            sayWord("Вы угадали с " + tryNumber + "-й попытки!!!");
            sayWord("Это - Удача!!!");
            gameRate = (byte) ((byte)10 - tryNumber);
            if (gameRate <=0) {
                gameRate = (byte) 1;
            }
        } else {
            sayWord("ERROR!!!");
        }
        game.setRunning(false);
        user.setGame(game);
        double totalRate = (game.getTotalRate() + getRate())/2;
        game.setTotalRate(totalRate);
        sayWord("Ваш счет за текущую игру составил " + getRate() + ", а общий счет - " + totalRate);
        random = 0;
        tryNumber = 0;
        gameRate = 0;

    }




    //TECH //TECH //TECH //TECH //TECH //TECH //TECH //TECH //TECH //TECH //TECH //TECH //TECH //TECH //TECH //TECH

    public void save (User user) {
        authService.update(user, user.getId());
    }

    public int getRate () {
        if (tryNumber == 1) {
            return 50;
        } else if (tryNumber == 2) {
            return 30;
        } else if (tryNumber == 3) {
            return 20;
        } else if (tryNumber > 3 && tryNumber <9) {
            return 10 - tryNumber;
        } else return 1;
    }

    public void sayWord (String word) {
        allWords.add(word);
    }

    public List <String> passWord () {
        List<String> reversedList = new ArrayList<>();
        for (int i = allWords.size() - 1; i >= 0; i--) {
            reversedList.add(allWords.get(i));
        }
        System.out.println(reversedList);
        return reversedList.stream().limit(MAX_SIZE).collect(Collectors.toList());
    }

}
