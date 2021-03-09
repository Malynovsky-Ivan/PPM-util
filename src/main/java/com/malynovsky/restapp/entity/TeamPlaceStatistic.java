package com.malynovsky.restapp.entity;

public class TeamPlaceStatistic {

    private int teamId;

    private int seasonNumber;

    private int position;

    public TeamPlaceStatistic(int teamId, int seasonNumber, int position) {
        this.teamId = teamId;
        this.seasonNumber = seasonNumber;
        this.position = position;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
