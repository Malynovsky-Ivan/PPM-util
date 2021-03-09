package com.malynovsky.restapp.api.impl;

import com.malynovsky.api.dto.CountryStatistic;
import com.malynovsky.api.entity.Player;
import com.malynovsky.api.filter.PlayerFilter;
import com.malynovsky.api.parsers.CountryAcronymParser;
import com.malynovsky.api.parsers.Parser;
import com.malynovsky.api.parsers.statistic.WorldCupStatisticParser;
import com.malynovsky.restapp.api.CountryService;
import com.malynovsky.restapp.api.PlayerStatisticService;
import com.malynovsky.restapp.api.TeamPositionService;
import com.malynovsky.restapp.dto.CountryWorldCupHistory;
import com.malynovsky.restapp.service.PlayerService;
import com.malynovsky.restapp.util.WorldCupFilter;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CountryServiceImpl implements CountryService {
    private static Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class);

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamPositionService teamService;
    @Autowired
    private PlayerStatisticService playerStatisticService;

    private Parser<List<String>> parser;
    private Parser<List<CountryStatistic>> parserCountryStatistic;

    public CountryServiceImpl() {
        parser = new CountryAcronymParser();
        parserCountryStatistic = new WorldCupStatisticParser();
    }

    @Override
    public Map<Integer, List<Player>> getAllByAgeInLeague(String countryAcronym, Predicate<Player> filter) {
        Predicate<Player> finalFilter = filter == null ? player -> true : filter;

        return teamService.getTeamUrl(countryAcronym).stream()
                .map(s -> playerStatisticService.getAllPlayersByAge(s, finalFilter))
                .filter(Objects::nonNull)
                .flatMap(integerListMap -> integerListMap.values().stream())
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Player::getAge));
    }

    @Override
    public List<String> getAllCountryAcronyms() {
        final String link = "https://basketball.powerplaymanager.com/en/league.html?data=b-ukr-i-1-1-l";

        try {
            return parser.parse(Jsoup.connect(link).get());
        } catch (IOException e) {
            System.out.println("!!!!!!!!");
        }

        return null;
    }

    @Override
    public Map<Integer, List<Player>> getAllByAgeForAll() {
        return getAllByAgeForAll(null);
    }

    @Override
    public Map<Integer, List<Player>> getAllByAgeForCountry(PlayerFilter playerFilter) {
        var result = getAllByAgeForAll(playerFilter);

        result.values().stream()
                .flatMap(Collection::stream)
                .map(com.malynovsky.restapp.entity.Player::new)
                .forEach(playerService::save);

        return result;
    }

    @Override
    public List<CountryWorldCupHistory> getHistoryByCompetition(WorldCupFilter filter) {

        List<CountryWorldCupHistory> result;

        if (filter.getNumber() == null) {
            Map<String, List<Integer>> histories = new HashMap<>();
            filter.getAllRelated().stream().filter(f -> f.getNumber() != null).forEach(f -> getWorldHistory(f, histories));
            result = convertWorldHistory(histories);
        } else {
            result = getAndConvertWorldHistory(filter);
        }

        return result;
    }

    private List<CountryWorldCupHistory> getAndConvertWorldHistory(WorldCupFilter filter) {
        return convertWorldHistory(getWorldHistory(filter, new HashMap<>()));
    }

    private Map<String, List<Integer>> getWorldHistory(WorldCupFilter filter, Map<String, List<Integer>> histories) {
        final String link = filter.getURI();

        for (int season = filter.getFirstSeason(); season <= filter.getEndSeason(); season++) {
            LOGGER.info("Season {}, tournament {}", season, filter.toString());
            try {
                parserCountryStatistic.parse(Jsoup.connect(String.format(link, season, filter.getNumber())).get())
                        .forEach(countryStatistic -> {
                            if (histories.containsKey(countryStatistic.getCountryName())) {
                                histories.get(countryStatistic.getCountryName()).add(countryStatistic.getPlace());
                            }
                            histories.putIfAbsent(countryStatistic.getCountryName(),
                                    Stream.of(countryStatistic.getPlace()).collect(Collectors.toList()));
                        });
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }

        return histories;
    }

    private List<CountryWorldCupHistory> convertWorldHistory(Map<String, List<Integer>> histories) {
        return histories.entrySet().stream().map(entry -> {
            CountryWorldCupHistory history = new CountryWorldCupHistory();
            history.setCountryName(entry.getKey());
            history.addPlaces(entry.getValue());
            return history;
        }).collect(Collectors.toList());
    }

    private Map<Integer, List<Player>> getAllByAgeForAll(PlayerFilter playerFilter) {
        return getAllCountryAcronyms().stream()
                .map(s -> getAllByAgeInLeague(s, playerFilter))
                .flatMap(integerListMap -> integerListMap.values().stream())
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Player::getAge));
    }
}
