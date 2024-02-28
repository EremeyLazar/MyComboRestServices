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

//    GameCommunicationService communication = new GameCommunicationService();

    private ArrayList<String> allWords = new ArrayList<>();

    private int random;
    private byte tryNumber;
    private int gameRate;
    private final int MAX_SIZE = 12;

    public void init(int answer) {
        User currentUser = gameService.getCurrentUser();
        if (!currentUser.getGame().isRunning()) {
            setInitialParameters(currentUser);
        }
        tryNumber++;
        if (answer < random) {
            responseWhenAnswerLessRandom(answer);
        } else if (answer > random) {
            responseWhenAnswerMoreRandom(answer);
        } else {
            finishGame(currentUser);
        }
    }


    public void finishGame(User user) {
        byte level = user.getGame().getLevel();
        try {
            switch (tryNumber) {
                case 1:
                    sayFirstTry(level);
                    break;
                case 2:
                    saySecondTry(level);
                    break;
                case 3:
                    sayThirdTry(level);
                    break;
                default:
                    if (tryNumber < 200) {
                        sayAnyTry(level);
                    } else {
                        sayWord("SOMETHING IS WRONG!!!");
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
        int BOUND = getBOUND(user.getGame().getLevel());
        allWords.clear();
        random = randomGenerator.nextInt(BOUND);
        System.out.println(random);
        tryNumber = 0;
        gameRate = 0;
        user.getGame().setRunning(true);
        sayWord("Число загадано от 0 до " + BOUND);
    }

    public void unsetInitialParameters(User user) {
        Game game = user.getGame();
        byte level = game.getLevel();
        int thisGameRate = getScore(level);
        game.setRunning(false);
        double totalRate = (game.getTotalRate() == 0) ? thisGameRate
                : (double) Math.round((game.getTotalRate() + thisGameRate) / 2 * 100) / 100;
        game.setLevel(checkLevel(game.getLevel(), totalRate));
        game.setTotalRate(totalRate);
        sayWord("Ваш счет за текущую игру составил " + thisGameRate + ", а общий счет - " + totalRate);
        random = 0;
        tryNumber = 0;
        gameRate = 0;
        game.setGames(game.getGames()+1);
        user.setGame(game);
        save(user);
    }

    public void sayFirstTry(byte level) {
        gameRate = getScore(level);
        sayWord("Поздравляю!");
        sayWord("Вы получаете 50 (!!!) очков!!!");
        sayWord("ВАУ!!! 777!!! БИНГО!!! Вы угадали с первой попытки!!!");
    }

    public void saySecondTry(byte level) {
        gameRate = getScore(level);
        sayWord("Поздравляю!");
        sayWord("Вы получаете " + gameRate +" (!!!) очков!!!");
        sayWord("Офигеть!!! Вы угадали со второй попытки!!!");
    }

    public void sayThirdTry(byte level) {
        gameRate = getScore(level);
        sayWord("Поздравляю!");
        sayWord("Вы получаете " + gameRate +" (!!!) очков!!!");
        sayWord("Офигеть!!! Вы угадали с третьей попытки!!!");
    }

    public void sayAnyTry(byte level) {
        gameRate = getScore(level);
        sayWord("Не останавливайтесь!");
        sayWord("Вы угадали с " + tryNumber + "-й попытки!!!");
        sayWord("Это - Удача!!!");
    }

    public void responseWhenAnswerLessRandom(int answer) {
        sayWord("Число " + answer + " МЕНЬШЕ загаданного!!");
        sayWord("Попытка № " + tryNumber);
    }

    public void responseWhenAnswerMoreRandom(int answer) {
        sayWord("Число " + answer + " БОЛЬШЕ загаданного!!");
        sayWord("Попытка № " + tryNumber);
    }

   public int getBOUND (byte level) {
        switch (level) {
            case 1:
                return 100;
            case 2:
                return 300;
            case 3:
                return 1000;
            default:
                return 3;
        }
   }

   public byte checkLevel (byte level, double totalRate) {
        if (level ==1 && totalRate > 10 || level == 2 && totalRate >120 || level == 0) {
            ++level;
            sayLevelIncrease(level, totalRate);
            return level;
        } else return level;
   }

   public void sayLevelIncrease (byte level, double totalRate) {
       sayWord("Поздравляем, ваш общий счет составил " + totalRate + " баллов и вы переходите на \n" + level + "-й уровень!!!");
   }

   public int getScore (byte level) {
        switch (level) {
            case 1:
                return getScoreFirstLevel();
            case 2:
                return getScoreSecondLevel();
            case 3:
                return getScoreThirdLevel();
            default: return Math.max(1, 10 - tryNumber);
        }
   }

   public int getScoreFirstLevel () {
        switch (tryNumber) {
            case 1:
                return 30;
            case 2:
                return 25;
            case 3:
                return 15;
            default:
                return Math.max(1, 10 - tryNumber);
        }
   }

    public int getScoreSecondLevel () {
        switch (tryNumber) {
            case 1:
                return 150;
            case 2:
                return 120;
            case 3:
                return 100;
            default:
                return Math.max(1, (200 - tryNumber)/3);
        }
    }

    public int getScoreThirdLevel () {
        switch (tryNumber) {
            case 1:
                return 500;
            case 2:
                return 400;
            case 3:
                return 300;
            default:
                return Math.max(1, (1000 - tryNumber)/3);
        }
    }


}
