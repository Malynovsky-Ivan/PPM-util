package com.malynovsky.restapp.api;

import com.malynovsky.api.dto.TeamAggregatedStatistic;

import java.util.List;
import java.util.Set;

public interface TeamPositionService {

    Set<String> getTeamNamesSortedByPositions(int seasonNumber, String countryAcronym);

    List<TeamAggregatedStatistic> getIntCupParticipantsStatistic(int seasonNumber, String countryAcronym);

    List<TeamAggregatedStatistic> getTeamRegularStatistic(int seasonNumber, String countryAcronym);

    List<String> getTeamUrl(String countryAcronym);
}
