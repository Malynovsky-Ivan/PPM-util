package com.malynovsky.restapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.malynovsky.restapp.reporting.ReportItem;
import com.malynovsky.restapp.reporting.Reportable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player implements Reportable {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String fullName;

    @Column
    private String teamUrl;

    @Column
    private String country;

    @Column
    private String playerUrl;

    @Column
    private int age;

    @Column
    private int height;

    @Column
    private int career;

    @Column
    private int overall;

    public Player() {
    }

    @Deprecated
    public Player(com.malynovsky.api.entity.Player player) {
        this.fullName = player.getFullName();
        this.teamUrl = player.getTeamUrl();
        this.country = player.getCountry();
        this.playerUrl = player.getPlayerUrl();
        this.age = player.getAge();
        this.height = player.getHeight();
        this.career = player.getCareer();
        this.overall = player.getOverall();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountry() {
        return country;
    }

    public String getTeamUrl() {
        return teamUrl;
    }

    public void setTeamUrl(String teamUrl) {
        this.teamUrl = teamUrl;
    }

    public String getPlayerUrl() {
        return playerUrl;
    }

    public void setPlayerUrl(String playerUrl) {
        this.playerUrl = playerUrl;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCareer() {
        return career;
    }

    public void setCareer(int career) {
        this.career = career;
    }

    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall) {
        this.overall = overall;
    }

    @Override
    @JsonIgnore
    public List<ReportItem> getData() { //TODO use reflexion
        List<ReportItem> items = new ArrayList<>();

        items.add(new ReportItem(this.fullName));
        items.add(new ReportItem(this.playerUrl));
        items.add(new ReportItem(this.teamUrl));
        items.add(new ReportItem(this.age));
        items.add(new ReportItem(this.career));
        items.add(new ReportItem(this.country));
        items.add(new ReportItem(this.height));
        items.add(new ReportItem(this.overall));

        return items;
    }
}

