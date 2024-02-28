//package ru.kata.spring.boot_security.demo.function_service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ru.kata.spring.boot_security.demo.model.Game;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.service.GameService;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.stream.Collectors;
//
//
//@Service
//public class GameCommunicationService {
//
//    private ArrayList<String> allWords = new ArrayList<>();
//
//    private final int MAX_SIZE = 12;
//
//    public ArrayList<String> getAllWords() {
//        return allWords;
//    }
//
//    public void setAllWords(String word) {
//        allWords.add(word);
//    }
//
//    public void clear () {
//        allWords.clear();
//    }
//
//
//    public void sayWord(String word) {
//        allWords.add(word);
//    }
//
//    public List<String> passWord() {
//        List<String> reversedList = new ArrayList<>();
//        for (int i = allWords.size() - 1; i >= 0; i--) {
//            reversedList.add(allWords.get(i));
//        }
//        return reversedList.stream().limit(MAX_SIZE).collect(Collectors.toList());
//    }
//
//    public void sayFirstTry(byte level) {
//        gameRate = getScore(level);
//        sayWord("Поздравляю!");
//        sayWord("Вы получаете 50 (!!!) очков!!!");
//        sayWord("ВАУ!!! 777!!! БИНГО!!! Вы угадали с первой попытки!!!");
//    }
//
//    public void saySecondTry(byte level) {
//        gameRate = getScore(level);
//        sayWord("Поздравляю!");
//        sayWord("Вы получаете " + gameRate +" (!!!) очков!!!");
//        sayWord("Офигеть!!! Вы угадали со второй попытки!!!");
//    }
//
//    public void sayThirdTry(byte level) {
//        gameRate = getScore(level);
//        sayWord("Поздравляю!");
//        sayWord("Вы получаете " + gameRate +" (!!!) очков!!!");
//        sayWord("Офигеть!!! Вы угадали с третьей попытки!!!");
//    }
//
//    public void sayAnyTry(byte level) {
//        gameRate = getScore(level);
//        sayWord("Не останавливайтесь!");
//        sayWord("Вы угадали с " + tryNumber + "-й попытки!!!");
//        sayWord("Это - Удача!!!");
//    }
//
//    public void responseWhenAnswerLessRandom(int answer) {
//        sayWord("Число " + answer + " МЕНЬШЕ загаданного!!");
//        sayWord("Попытка № " + tryNumber);
//    }
//
//    public void responseWhenAnswerMoreRandom(int answer) {
//        sayWord("Число " + answer + " БОЛЬШЕ загаданного!!");
//        sayWord("Попытка № " + tryNumber);
//    }
//
//   public void sayLevelIncrease (byte level, double totalRate) {
//       sayWord("Поздравляем, ваш общий счет составил " + totalRate + " баллов и вы переходите на \n" + level + "-й уровень!!!");
//   }
//
//
//}
