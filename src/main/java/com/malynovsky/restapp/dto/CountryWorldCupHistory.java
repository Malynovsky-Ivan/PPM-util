package com.malynovsky.restapp.dto;

import java.util.List;

public class CountryWorldCupHistory implements Comparable<CountryWorldCupHistory> {
    private String countryName;

    private int seasonCount = 0;
    private int firstPlace = 0;
    private int secondPlace = 0;
    private int thirdPlace = 0;
    private int semiFinalCount = 0;
    private int quarterFinalCount = 0;
    private int playOffCount = 0;
    private int bestPlace = 25;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getSeasonCount() {
        return seasonCount;
    }

    public void setSeasonCount(int seasonCount) {
        this.seasonCount = seasonCount;
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

    public int getThirdPlace() {
        return thirdPlace;
    }

    public void setThirdPlace(int thirdPlace) {
        this.thirdPlace = thirdPlace;
    }

    public int getBestPlace() {
        return bestPlace;
    }

    public void setBestPlace(int bestPlace) {
        this.bestPlace = bestPlace;
    }

    public void addPlaces(List<Integer> value) {
        this.seasonCount = value.size();
        for (Integer place : value) {
            this.bestPlace = Math.min(this.bestPlace, place);
            this.firstPlace += place == 1 ? 1 : 0;
            this.secondPlace += place == 2 ? 1 : 0;
            this.thirdPlace += place == 3 ? 1 : 0;
            this.semiFinalCount += (place <= 4) ? 1 : 0;
            this.quarterFinalCount += (place <= 8) ? 1 : 0;
            this.playOffCount += (place <= 16) ? 1 : 0;
        }
    }

    public int getQuarterFinalCount() {
        return quarterFinalCount;
    }

    public void setQuarterFinalCount(int quarterFinalCount) {
        this.quarterFinalCount = quarterFinalCount;
    }

    public int getPlayOffCount() {
        return playOffCount;
    }

    public void setPlayOffCount(int playOffCount) {
        this.playOffCount = playOffCount;
    }

    public int getSemiFinalCount() {
        return semiFinalCount;
    }

    public void setSemiFinalCount(int semiFinalCount) {
        this.semiFinalCount = semiFinalCount;
    }

    @Override
    public int compareTo(CountryWorldCupHistory o) {
        int result = Integer.compare(firstPlace, o.getFirstPlace());

        if (result == 0) {
            result = Integer.compare(secondPlace, o.getSecondPlace());
        }
        if (result == 0) {
            result = Integer.compare(thirdPlace, o.getThirdPlace());
        }
        if (result == 0) {
            result = -Integer.compare(bestPlace, o.getBestPlace());
        }
        if (result == 0) {
            result = Integer.compare(semiFinalCount, o.getSemiFinalCount());
        }
        if (result == 0) {
            result = Integer.compare(quarterFinalCount, o.getQuarterFinalCount());
        }
        if (result == 0) {
            result = Integer.compare(playOffCount, o.getPlayOffCount());
        }
        if (result == 0) {
            result = Integer.compare(seasonCount, o.getSeasonCount());
        }

        return -result;
    }
}
