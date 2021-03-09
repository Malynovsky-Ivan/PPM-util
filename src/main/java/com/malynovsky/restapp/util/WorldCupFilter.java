package com.malynovsky.restapp.util;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum WorldCupFilter {
    BASKETBALL_U17(3, 7),
    BASKETBALL_U19(2, 7),
    BASKETBALL_MAIN(1, 3),
    BASKETBALL_ALL,

    HOCKEY_U18(3, 11),
    HOCKEY_U20(2, 11),
    HOCKEY_MAIN(1, 3),
    HOCKEY_ALL;

    private Integer number;
    private int firstSeason;

    private static Set<WorldCupFilter> BASKET_FILTERS = Stream
            .of(BASKETBALL_MAIN, BASKETBALL_U17, BASKETBALL_U19, BASKETBALL_ALL).collect(Collectors.toSet());
    private static Set<WorldCupFilter> HOCKEY_FILTERS = Stream
            .of(HOCKEY_MAIN, HOCKEY_U18, HOCKEY_U20, HOCKEY_ALL).collect(Collectors.toSet());

    WorldCupFilter(Integer number, int firstSeason) {
        this.number = number;
        this.firstSeason = firstSeason;
    }

    WorldCupFilter() {
    }

    public static Set<WorldCupFilter> getBasketFilters() {
        return BASKET_FILTERS;
    }

    public static Set<WorldCupFilter> getHockeyFilters() {
        return HOCKEY_FILTERS;
    }

    public Integer getNumber() {
        return number;
    }

    public int getFirstSeason() {
        return firstSeason;
    }

    public String getURI() {
        if (BASKET_FILTERS.contains(this)) {
            return "https://basketball.powerplaymanager.com/en/world-cup.html?data=playoff1-%d-%d-1";
        } else if (HOCKEY_FILTERS.contains(this)) {
            return "https://hockey.powerplaymanager.com/en/world-championships.html?data=playoff2-1-%d-%d-1";
        }

        throw new IllegalArgumentException();
    }

    public Set<WorldCupFilter> getAllRelated() {
        if (BASKET_FILTERS.contains(this)) {
            return BASKET_FILTERS;
        } else if (HOCKEY_FILTERS.contains(this)) {
            return HOCKEY_FILTERS;
        }

        throw new IllegalArgumentException();
    }

    public int getEndSeason() {
        if (HOCKEY_FILTERS.contains(this)) {
            return 37;
        }

        return 32; //basketball
    }
}
