package com.malynovsky.restapp.api;

import com.malynovsky.api.entity.Player;
import com.malynovsky.api.filter.PlayerFilter;
import com.malynovsky.restapp.dto.CountryWorldCupHistory;
import com.malynovsky.restapp.util.WorldCupFilter;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface CountryService {

    Map<Integer, List<Player>> getAllByAgeInLeague(String countryAcronym, Predicate<Player> filter);

    List<String> getAllCountryAcronyms();

    Map<Integer, List<Player>> getAllByAgeForAll();

    Map<Integer, List<Player>> getAllByAgeForCountry(PlayerFilter filter);

    List<CountryWorldCupHistory> getHistoryByCompetition(WorldCupFilter filter);
}
