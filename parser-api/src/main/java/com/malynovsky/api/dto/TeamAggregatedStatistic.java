package com.malynovsky.api.dto;

public class TeamAggregatedStatistic implements Comparable<TeamAggregatedStatistic> {
    private String teamName;

    private int seasonCount;
    private int firstPlaceCount;
    private int secondPlaceCount;
    private int thirdPlaceCount;
    private double averagePlace;

    private int theBestPlace;

    private int gamesPlayed;
    private int matchesWon;
    private int matchesLost;

    private int pointsScored;
    private int pointsAllowed;

    public TeamAggregatedStatistic(String name, int position) {
        teamName = name;
        update(position);
    }

    public TeamAggregatedStatistic(String name, int season, int position) {
        teamName = name;
        this.gamesPlayed = 0;
        this.matchesWon = 0;
        this.matchesLost = 0;
        this.pointsAllowed = 0;
        this.pointsScored = 0;
        update(position);
    }

    //TODO Builder
    public void update(int position) {
        seasonCount++;
        switch (position) {
            case 1:
                firstPlaceCount++;
                break;
            case 2:
                secondPlaceCount++;
                break;
            case 3:
                thirdPlaceCount++;
                break;
        }

        if (theBestPlace == 0 || theBestPlace > position) {
            theBestPlace = position;
        }

        averagePlace = (averagePlace * (seasonCount - 1) + position) / seasonCount;
    }

    public void update(int gamesPlayed, int gamesWon, int pointsScored, int pointsAllowed) {
        this.gamesPlayed += gamesPlayed;
        this.matchesWon += gamesWon;
        this.matchesLost += gamesPlayed - gamesWon;
        this.pointsAllowed += pointsAllowed;
        this.pointsScored += pointsScored;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getSeasonCount() {
        return seasonCount;
    }

    public void setSeasonCount(int seasonCount) {
        this.seasonCount = seasonCount;
    }

    public int getFirstPlaceCount() {
        return firstPlaceCount;
    }

    public void setFirstPlaceCount(int firstPlaceCount) {
        this.firstPlaceCount = firstPlaceCount;
    }

    public int getSecondPlaceCount() {
        return secondPlaceCount;
    }

    public void setSecondPlaceCount(int secondPlaceCount) {
        this.secondPlaceCount = secondPlaceCount;
    }

    public int getThirdPlaceCount() {
        return thirdPlaceCount;
    }

    public void setThirdPlaceCount(int thirdPlaceCount) {
        this.thirdPlaceCount = thirdPlaceCount;
    }

    public double getAveragePlace() {
        return averagePlace;
    }

    public void setAveragePlace(double averagePlace) {
        this.averagePlace = averagePlace;
    }

    public int getTheBestPlace() {
        return theBestPlace;
    }

    public void setTheBestPlace(int theBestPlace) {
        this.theBestPlace = theBestPlace;
    }

    public int getPointsScored() {
        return pointsScored;
    }

    public void setPointsScored(int pointsScored) {
        this.pointsScored = pointsScored;
    }

    public int getPointsAllowed() {
        return pointsAllowed;
    }

    public void setPointsAllowed(int pointsAllowed) {
        this.pointsAllowed = pointsAllowed;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public void setMatchesWon(int matchesWon) {
        this.matchesWon = matchesWon;
    }

    @Override
    public int compareTo(TeamAggregatedStatistic o) {
        int result = Integer.compare(firstPlaceCount, o.getFirstPlaceCount());

        if (result == 0) {
            result = Integer.compare(secondPlaceCount, o.getSecondPlaceCount());
        }
        if (result == 0) {
            result = Integer.compare(thirdPlaceCount, o.getThirdPlaceCount());
        }
        if (result == 0) {
            result = -Double.compare(averagePlace, o.getAveragePlace());
        }

        return -result;
    }
}
