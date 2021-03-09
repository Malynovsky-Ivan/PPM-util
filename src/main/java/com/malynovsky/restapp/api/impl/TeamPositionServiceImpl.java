package com.malynovsky.restapp.api.impl;

import com.malynovsky.api.dto.TeamAggregatedStatistic;
import com.malynovsky.api.parsers.Parser;
import com.malynovsky.api.parsers.TeamNamesParser;
import com.malynovsky.api.parsers.statistic.TeamPositionParser;
import com.malynovsky.api.parsers.statistic.TeamRegularSeasonParser;
import com.malynovsky.restapp.api.TeamPositionService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TeamPositionServiceImpl implements TeamPositionService {
    private static Logger LOGGER = LoggerFactory.getLogger(TeamPositionService.class);

    private static final String FINAL_LEAGUE_RANKING
            = "https://basketball.powerplaymanager.com/en/final-ranking.html?data=%s-i-1-%d";
    private static final String FINAL_REGULAR_SEASON_RANKING
            = "https://basketball.powerplaymanager.com/en/league.html?data=b-%s-%s-%d-%d-l";
    private static final String FINAL_INTERNATIONAL_CUP_RANKING
            = "https://basketball.powerplaymanager.com/en/international-cup-participants-by-country.html?data=teams-%s-%d";

    private Parser<Set<String>> offSeasonParser;
    private Parser<List<String>> teamNamesParser;
    private Parser<List<TeamAggregatedStatistic>> regularSeasonParser;

    public TeamPositionServiceImpl() {
        offSeasonParser = new TeamPositionParser();
        teamNamesParser = new TeamNamesParser();
        regularSeasonParser = new TeamRegularSeasonParser();
    }

    @Override
    public Set<String> getTeamNamesSortedByPositions(int seasonNumber, String countryAcronym) {
        try {
            Document document = Jsoup.connect(String.format(FINAL_LEAGUE_RANKING, countryAcronym, seasonNumber)).get();
            return offSeasonParser.parse(document);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<TeamAggregatedStatistic> getIntCupParticipantsStatistic(int seasonNumber, String countryAcronym) {
        try {
            Document document = Jsoup.connect(String.format(FINAL_INTERNATIONAL_CUP_RANKING, countryAcronym,
                    seasonNumber)).get();
            return regularSeasonParser.parse(document);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<TeamAggregatedStatistic> getTeamRegularStatistic(int seasonNumber, String countryAcronym) {
        try {
            Document document = Jsoup.connect(String.format(FINAL_REGULAR_SEASON_RANKING, countryAcronym, "i", 1,
                    seasonNumber)).get();
            return regularSeasonParser.parse(document);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<String> getTeamUrl(String countryAcronym) {
        LOGGER.info("Looking for team names for {}", countryAcronym);

        List<String> result = new ArrayList<>();

        try {
            String leagueLevel = "i";
            for (int level = 1; level <= 3; level++) {
                for (int league = 1; league <= Math.pow(3, level - 1); league++) {
                    Document document = Jsoup.connect(String.format(FINAL_REGULAR_SEASON_RANKING, countryAcronym,
                            leagueLevel, league, 29)).get();
                    result.addAll(teamNamesParser.parse(document));
                }
                leagueLevel += "i";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TeamNamesParser.NoSuchLeagueException e) {
            LOGGER.warn(e.getMessage());
        }

        return result;
    }
}
