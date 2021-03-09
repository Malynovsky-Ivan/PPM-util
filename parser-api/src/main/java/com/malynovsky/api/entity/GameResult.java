package com.malynovsky.api.entity;

import java.util.List;

/**
 * Created by Ivan on 28.05.2019 - 21:55.
 * ppm-telegram-bot
 */
public class GameResult implements Game {
    private Team host;
    private Team visitor;

    public GameResult() {
    }

    public GameResult(String hostName, String guestName) {
        setTeams(hostName, guestName);
    }

    public GameResult(List<String> teamNames) {
        if (teamNames.size() != 2) {
            throw new RuntimeException();
        }

        setTeams(teamNames.get(0), teamNames.get(1));
    }

    public void setTeams(String hostName, String guestName) {
        host = new Team(hostName);
        visitor = new Team(guestName);
    }

    public void updateScores(int points, String team) {
        if (team.equals("1")) {
            host.append(points);
        } else {
            visitor.append(points);
        }
    }

    @Override
    public String getResult() {
        return toString();
    }

    @Override
    public String toString() {
        return host.toString() + " - " + visitor.toString();
    }
}


