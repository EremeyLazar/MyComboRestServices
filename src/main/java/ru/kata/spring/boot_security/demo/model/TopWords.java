package ru.kata.spring.boot_security.demo.model;

public class TopWords {

    private String word;
    private Integer limit;

    public TopWords() {
    }

    public TopWords(String message, Integer limit) {
        this.word = message;
        this.limit = limit;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
