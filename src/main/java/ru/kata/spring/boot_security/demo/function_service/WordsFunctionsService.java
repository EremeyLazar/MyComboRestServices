package ru.kata.spring.boot_security.demo.function_service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordsFunctionsService {

    public Map<String, Integer> processWords(String initialText, int limit) {

        try (BufferedReader reader = new BufferedReader(new StringReader(initialText))) {
            return reader.lines()
                    .flatMap(l -> Stream.of(l.toLowerCase().replaceAll("[^a-zа-я0-9]", " ").split("[\\p{Punct}\\s]+")))
                    .filter(word -> !word.isEmpty())
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)))
                    .entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .limit(limit)
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().intValue(), (e1, e2) -> e1, LinkedHashMap::new));

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
