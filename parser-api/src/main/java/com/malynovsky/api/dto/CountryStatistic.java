package com.malynovsky.api.dto;

import java.util.List;

public class CountryStatistic {
    private String countryName;
    private int place;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
