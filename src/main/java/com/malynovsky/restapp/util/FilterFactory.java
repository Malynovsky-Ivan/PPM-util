package com.malynovsky.restapp.util;

import java.util.Set;

public class FilterFactory {

    private FilterFactory() {
    }

    public static WorldCupFilter getFilter(String sport, Integer level) {
        if (sport == null) {
            throw new IllegalArgumentException();
        }

        switch (sport) {
            case "hockey":
                return getFilter(WorldCupFilter.getHockeyFilters(), level);
            case "basketball":
                return getFilter(WorldCupFilter.getBasketFilters(), level);
            default:
                return null;
        }
    }

    private static WorldCupFilter getFilter(Set<WorldCupFilter> filters, Integer level) {
        return filters.stream()
                .filter(filter -> filter.getNumber() == level).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
