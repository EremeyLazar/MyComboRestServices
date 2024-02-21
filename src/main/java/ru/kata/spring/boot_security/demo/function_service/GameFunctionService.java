package ru.kata.spring.boot_security.demo.function_service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Game;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.GameService;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class GameFunctionService {


    @Autowired
    private GameService gameService;

    private ArrayList<String> allWords = new ArrayList<>();

    private int random;
    private byte tryNumber;
    private byte gameRate;
    private final int MAX_SIZE = 12;

    public void init(int answer) {
        User currentUser = gameService.getCurrentUser();
        if (!currentUser.getGame().isRunning()) {
            setInitialParameters(currentUser);
        }
        tryNumber++;
        try {
            if (answer < random) {
                responseWhenAnswerLessRandom(answer);
            } else if (answer > random) {
                responseWhenAnswerMoreRandom(answer);
            } else {
                finishGame(currentUser);
            }
        } catch (java.util.InputMismatchException exception) {
            sayWord("Загадано число, а не слово... Вай меееее....");
        }
    }

    public void finishGame(User user) {
        try {
            switch (tryNumber) {
                case 1:
                    sayFirstTry();
                    break;
                case 2:
                    saySecondTry();
                    break;
                case 3:
                    sayThirdTry();
                    break;
                default:
                    if (tryNumber < 100) {
                        sayAnyTry();
                    } else {
                        sayWord("ERROR!!!");
                    }
            }
            unsetInitialParameters(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            if (ex instanceof InvocationTargetException) {
                Throwable cause = ((InvocationTargetException) ex).getCause();
                System.err.println("Root cause: " + (cause != null ? cause.getMessage() : "Unknown"));
            } else {
                System.err.println("Exception: " + ex.getMessage());
            }
        }
    }

    public void save(User user) {
        gameService.save(user);
    }

    public int getRate() {
        switch (tryNumber) {
            case 1:
                return 50;
            case 2:
                return 30;
            case 3:
                return 20;
            default:
                return (tryNumber > 3 && tryNumber < 9) ? 10 - tryNumber : 1;
        }
    }


    public void sayWord(String word) {
        allWords.add(word);
    }

    public List<String> passWord() {
        List<String> reversedList = new ArrayList<>();
        for (int i = allWords.size() - 1; i >= 0; i--) {
            reversedList.add(allWords.get(i));
        }
        return reversedList.stream().limit(MAX_SIZE).collect(Collectors.toList());
    }

    public void setInitialParameters(User user) {
        Random randomGenerator = new Random();
        allWords.clear();
        random = randomGenerator.nextInt(100);
        System.out.println(random);
        tryNumber = 0;
        gameRate = 0;
        user.getGame().setRunning(true);
    }

    public void unsetInitialParameters(User user) {
        Game game = user.getGame();
        game.setRunning(false);
        user.setGame(game);
        double totalRate = (game.getTotalRate() == 0) ? getRate() : (game.getTotalRate() + getRate()) / 2;
        game.setTotalRate(totalRate);
        sayWord("Ваш счет за текущую игру составил " + getRate() + ", а общий счет - " + totalRate);
        random = 0;
        tryNumber = 0;
        gameRate = 0;
        save(user);
    }

    public void sayFirstTry() {
        sayWord("Поздравляю!");
        sayWord("Вы получаете 50 (!!!) очков!!!");
        sayWord("ВАУ!!! 777!!! БИНГО!!! Вы угадали с первой попытки!!!");
        gameRate = 50;
    }

    public void saySecondTry() {
        sayWord("Поздравляю!");
        sayWord("Вы получаете 30 (!!!) очков!!!");
        sayWord("Офигеть!!! Вы угадали со второй попытки!!!");
        gameRate = 30;
    }

    public void sayThirdTry() {
        sayWord("Поздравляю!");
        sayWord("Вы получаете 20 (!!!) очков!!!");
        sayWord("Офигеть!!! Вы угадали с третьей попытки!!!");
        gameRate = 20;
    }

    public void sayAnyTry() {
        sayWord("Не останавливайтесь!");
        sayWord("Вы угадали с " + tryNumber + "-й попытки!!!");
        sayWord("Это - Удача!!!");
        gameRate = (byte) Math.max(1, 10 - tryNumber);
    }

    public void responseWhenAnswerLessRandom(int answer) {
        sayWord("Число " + answer + " МЕНЬШЕ загаданного!!");
        sayWord("Попытка № " + tryNumber);
    }

    public void responseWhenAnswerMoreRandom(int answer) {
        sayWord("Число " + answer + " БОЛЬШЕ загаданного!!");
        sayWord("Попытка № " + tryNumber);
    }
}
