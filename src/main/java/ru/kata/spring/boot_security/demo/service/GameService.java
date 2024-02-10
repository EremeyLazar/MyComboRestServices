package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Game;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.GameRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Random;

@Service
@Transactional
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    UserService userService = new UserService();


    public void init (byte answer) {
        System.out.println("INIT ПОЛУЧИЛ " +  answer);
        Random randomGenerator = new Random();
        User currentUser = userService.getCurrentUser();
        Game game = gameRepository.findById(currentUser.getId()).orElse(null);


        if (game == null ) {
            game.getMessages().clear();
            game.setRunning(true);
            game.setRandom(randomGenerator.nextInt(100));
            game.setTryNumber((byte) 1);
            game.setGameRate((byte)0);
            game.setMessages(currentUser.getUsername()+ ", число загадано! Начинаем...");
            System.out.println("WE START.....................");
        }
        engine(game, answer);
    }

    public void engine (Game game, byte answer) {
        game.setMessages("Попытка № " + game.getTryNumber());

        try {
        if (answer < game.getRandom()) {
            game.setMessages("Не угадали, ВАШЕ число МЕНЬШЕ загаданного!!");
        } else if (answer > game.getRandom()) {
            game.setMessages("Не угадали, ВАШЕ число БОЛЬШЕ загаданного!!");
        } else if (answer == game.getRandom()) {
            finish(game);
        }
        } catch (java.util.InputMismatchException exception) {
            game.setMessages("Загадано число, а не слово... Вай меееее....");
            close(game);
        }
        game.setTryNumber((byte) (game.getTryNumber() + 1));
    }

    public void close (Game game) {
        game.setRunning(false);
    }

    public void finish (Game game) {
        byte num = game.getTryNumber();

        if (num == 1) {
            game.setMessages("ВАУ!!! 777!!! БИНГО!!! Вы угадали с первой попытки!!!");
            game.setMessages("Вы получаете 50 (!!!) очков!!!");
            game.setMessages("Поздравляю!");
            game.setGameRate((byte) 50);
        } else if (num == 2) {
            game.setMessages("Офигеть!!! Вы угадали со второй попытки!!!");
            game.setMessages("Вы получаете 30 (!!!) очков!!!");
            game.setMessages("Поздравляю!");
            game.setGameRate((byte) 30);
        } else if (num == 3) {
            game.setMessages("Офигеть!!! Вы угадали с третьей попытки!!!");
            game.setMessages("Вы получаете 20 (!!!) очков!!!");
            game.setMessages("Поздравляю!");
            game.setGameRate((byte) 20);
        } else if (num < 10) {
            game.setGameRate ((byte) ((byte) 10- num));
            game.setMessages("Удача!!!");
            game.setMessages("Вы получаете" + game.getGameRate() + " очков!!!");
            game.setMessages("Не останавливайтесь!");
        } else {
            game.setMessages("ERROR!!!");
            close(game);
        }

        game.setTotalRate((byte) ((game.getTotalRate() + game.getGameRate())/2));
        game.setMessages("Ваш общий рейтинг составляет: " + game.getTotalRate() + " баллов!!!");
        game.setMessages("Чтобы продолжить игру, пошлите любую цифру!");

        close(game);

    }


}
