package com.malynovsky.restapp.service;

import com.malynovsky.api.dto.TeamAggregatedStatistic;
import com.malynovsky.restapp.entity.Team;

import java.util.List;

public interface TeamService extends Service<Team> {

    Team getByName(String teamName);

    List<TeamAggregatedStatistic> generatePositionStatistic(String country);

    List<TeamAggregatedStatistic> generateRegularPositionStatistic(String country);

    List<TeamAggregatedStatistic> generateClubInternationalStatistic(String country);
}
