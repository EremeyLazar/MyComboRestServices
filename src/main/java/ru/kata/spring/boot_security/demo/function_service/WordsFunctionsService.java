package ru.kata.spring.boot_security.demo.function_service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordsFunctionsService {

    private static final Set<String> STOP_ENGLISH_WORDS = Set.of("in", "a", "the", "to", "and", "of", "for", "is", "are",
            "on", "by", "or", "an");
    private static final Set<String> STOP_RUSSIAN_WORDS = Set.of("в", "на", "и", "с", "из", "для");

    public Map<String, Integer> processWords(String initialText, int limit) {

        try (BufferedReader reader = new BufferedReader(new StringReader(initialText))) {
            return reader.lines()
                    .flatMap(l -> Stream.of(l.toLowerCase().replaceAll("[^a-zа-я0-9]", " ")
                            .split("[\\p{Punct}\\s]+")))
                    .filter(word -> !word.isEmpty() && !STOP_ENGLISH_WORDS.contains(word) && !STOP_RUSSIAN_WORDS.contains(word))
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
