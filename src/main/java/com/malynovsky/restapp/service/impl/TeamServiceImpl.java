package com.malynovsky.restapp.service.impl;

import com.malynovsky.restapp.api.TeamPositionService;
import com.malynovsky.api.dto.TeamAggregatedStatistic;
import com.malynovsky.restapp.entity.Team;
import com.malynovsky.restapp.repository.TeamRepository;
import com.malynovsky.restapp.service.AbstractService;
import com.malynovsky.restapp.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl extends AbstractService<Team> implements TeamService {
    public static final int CURRENT_SEASON = 32;

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamPositionService positionService;

    @Override
    public Team getByName(String teamName) {
        return teamRepository.getTeamByName(teamName);
    }

    @Override
    public List<TeamAggregatedStatistic> generatePositionStatistic(String country) {
        var result = new HashMap<String, TeamAggregatedStatistic>();

        for (int season = 1; season <= CURRENT_SEASON; season++) {
            Set<String> seasonResult = positionService.getTeamNamesSortedByPositions(season, country);

            updateStatistics(result, country, season, seasonResult);
        }

        return result.values().stream().sorted().collect(Collectors.toList());
    }

    @Override
    public List<TeamAggregatedStatistic> generateRegularPositionStatistic(String country) {
        var result = new HashMap<String, TeamAggregatedStatistic>();

        for (int season = 1; season <= CURRENT_SEASON; season++) {
            var seasonResult = positionService.getTeamRegularStatistic(season, country);
            for (int position = 1; position <= 16; position++) {
                TeamAggregatedStatistic newSeason = seasonResult.get(position - 1);
                if (result.containsKey(newSeason.getTeamName())) {
                    TeamAggregatedStatistic aggregatedStatistic = result.get(newSeason.getTeamName());
                    aggregatedStatistic.update(position);
                    aggregatedStatistic.update(newSeason.getGamesPlayed(), newSeason.getMatchesWon(),
                            newSeason.getPointsScored(), newSeason.getPointsAllowed());
                    result.put(newSeason.getTeamName(), aggregatedStatistic);
                } else {
                    result.put(newSeason.getTeamName(), newSeason);
                }
            }
        }

        return result.values().stream().sorted().collect(Collectors.toList());
    }

    @Override
    public List<TeamAggregatedStatistic> generateClubInternationalStatistic(String country) {
        return null;
    }

    private void updateStatistics(Map<String, TeamAggregatedStatistic> statistic, String country, int season, Set<String> seasonResult) {
        seasonResult.stream()
                .map(teamName -> {
                    Team team = getByName(teamName);
                    if (team == null) {
                        Team newTeam = new Team();
                        newTeam.setCountry(country);
                        newTeam.setName(teamName);
                        return newTeam;
                    }

                    return team;
                })
//                .map(team -> new TeamPlaceStatistic(team.getId(), season, getIndex(seasonResult, team.getName())))
                .forEach(team -> {
                    if (statistic.containsKey(team.getName())) {
                        TeamAggregatedStatistic aggregatedStatistic = statistic.get(team.getName());
                        aggregatedStatistic.update(getIndex(seasonResult, team.getName()));
                        statistic.put(team.getName(), aggregatedStatistic);
                    } else {
                        statistic.put(team.getName(), new TeamAggregatedStatistic(team.getName(), season,
                                getIndex(seasonResult, team.getName())));
                    }
                });
    }

    private int getIndex(Collection<String> seasonResult, String name) {
        int index = 0;
        for (String teamName : seasonResult) {
            if (teamName.equals(name)) {
                return index + 1;
            }
            index++;
        }

        return -1;
    }

    @Override
    protected JpaRepository<Team, Integer> getRepo() {
        return teamRepository;
    }

}
