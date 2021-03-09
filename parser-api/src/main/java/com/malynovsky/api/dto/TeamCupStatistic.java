package com.malynovsky.api.dto;

import com.malynovsky.api.util.TournamentType;

public class TeamCupStatistic implements Comparable<TeamCupStatistic> {
    private String teamName;

    private int seasonNumber;

    private int theHighestPlace;
    private int firstPlace;
    private int secondPlace;
    private int semiFinal;
    private int quarterFinal;

    private int gamesPlayed;
    private int gamesWon;

    public void update(int position) {
        seasonNumber++;
        switch (position) {
            case 1:
                firstPlace++;
                break;
            case 2:
                secondPlace++;
                break;
            case 3:
            case 4:
                semiFinal++;
                break;
            case 5:
                quarterFinal++;
                break;
        }

        if (theHighestPlace == 0 || theHighestPlace > position) {
            theHighestPlace = position;
        }
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getTheHighestPlace() {
        return theHighestPlace;
    }

    public void setTheHighestPlace(int theHighestPlace) {
        this.theHighestPlace = theHighestPlace;
    }

    public int getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(int firstPlace) {
        this.firstPlace = firstPlace;
    }

    public int getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(int secondPlace) {
        this.secondPlace = secondPlace;
    }

    public int getSemiFinal() {
        return semiFinal;
    }

    public void setSemiFinal(int semiFinal) {
        this.semiFinal = semiFinal;
    }

    public int getQuarterFinal() {
        return quarterFinal;
    }

    public void setQuarterFinal(int quarterFinal) {
        this.quarterFinal = quarterFinal;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    @Override
    public int compareTo(TeamCupStatistic o) {
        return 0;
    }
}
