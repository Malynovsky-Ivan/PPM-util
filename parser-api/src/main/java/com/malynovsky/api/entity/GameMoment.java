package com.malynovsky.api.entity;

/**
 * Created by Ivan on 29.05.2019 - 22:11.
 * ppm-telegram-bot
 */
public abstract class GameMoment<ACTION_TYPE> {
    int secondsInGame;

    public GameMoment(int secondsInGame) {
        this.secondsInGame = secondsInGame;
    }

    public boolean before(GameMoment gameMomentToCompare) {
        return secondsInGame < gameMomentToCompare.secondsInGame;
    }

    public boolean equals(GameMoment gameMomentToCompare) {
        return secondsInGame == gameMomentToCompare.secondsInGame;
    }

    public abstract String getTeam();
}
