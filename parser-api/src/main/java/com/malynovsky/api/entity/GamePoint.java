package com.malynovsky.api.entity;

/**
 * Created by Ivan on 29.05.2019 - 22:00.
 * ppm-telegram-bot
 */

public class GamePoint extends GameMoment {
    private int points;
    private String team;

    public GamePoint(int time, int points) {
        super(time);
        this.points = points;
    }

    public GamePoint(int time, String team) {
        super(time);
        this.team = team;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "GamePoint{" +
                "points=" + points +
                ", team='" + team + '\'' + secondsInGame +
                '}';
    }
}
