package com.malynovsky.api.entity;

public class Team {
    String name;
    int score;

    Team(String name) {
        this.name = name;
        this.score = 0;
    }

    void append(int points) {
        this.score += points;
    }

    @Override
    public String toString() {
        return String.format("%s (%d)", name, score);
    }
}
