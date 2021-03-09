package com.malynovsky.restapp.api;

import com.malynovsky.api.entity.Player;
import com.malynovsky.api.filter.PlayerFilter;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface PlayerStatisticService {

    Map<Integer, List<Player>> getAllPlayersByAge(String teamUrl);

    Map<Integer, List<Player>> getAllPlayersByAge(String teamUrl, Predicate<Player> filter);
}
