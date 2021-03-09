package com.malynovsky.restapp.api.impl;

import com.malynovsky.api.entity.Player;
import com.malynovsky.api.filter.PlayerFilter;
import com.malynovsky.api.parsers.statistic.PlayerOverallParser;
import com.malynovsky.restapp.api.PlayerStatisticService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class PlayerStatisticServiceImpl implements PlayerStatisticService {

    private PlayerOverallParser playerParser;

    public PlayerStatisticServiceImpl() {
        this.playerParser = new PlayerOverallParser();
    }

    @Override
    public Map<Integer, List<Player>> getAllPlayersByAge(String teamUrl) {

        return getAllPlayersByAge(teamUrl, player -> true);
    }

    @Override
    public Map<Integer, List<Player>> getAllPlayersByAge(String teamUrl, Predicate<Player> filter) {

        try {
            Document document = Jsoup.connect(teamUrl.replace("team", "players")).get();
            playerParser.setFilter(filter);
            return playerParser.parse(document)
                    .stream()
                    .peek(player -> player.setTeamUrl(teamUrl))
                    .collect(Collectors.groupingBy(Player::getAge));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
