package com.malynovsky.api.entity;

/**
 * Created by Ivan on 29.05.2019 - 23:34.
 * ppm-telegram-bot
 */
public interface Game {

    String getResult();

    void setTeams(String team1, String team2);
}
