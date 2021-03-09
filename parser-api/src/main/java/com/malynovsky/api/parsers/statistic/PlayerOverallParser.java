package com.malynovsky.api.parsers.statistic;

import com.malynovsky.api.entity.Player;
import com.malynovsky.api.parsers.FilterParser;
import com.malynovsky.api.parsers.Parser;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PlayerOverallParser implements Parser<List<Player>>, FilterParser<Player> {
    private Predicate<Player> filter; // TODO

    public PlayerOverallParser() {
    }

    @Override
    public void setFilter(Predicate<Player> filter) {
        this.filter = filter;
    }

    @Override
    public String parse(String url) throws IOException {
        return null;
    }

    @Override
    public List<Player> parse(Document document) throws IOException {
        return document.select("tbody").get(0).children()
                .stream()
                .map(element -> {
                    Player player = new Player();
                    player.setCountry(element.child(1).child(0).child(0).attr("title"));
                    player.setFullName(element.child(1).child(1).html());
                    player.setPlayerUrl(element.child(1).child(1).attr("href"));
                    player.setAge(Integer.parseInt(element.child(4).html()));
                    player.setHeight(Integer.parseInt(element.child(5).html()));
                    player.setCareer(Integer.parseInt(element.child(7).child(0).html().split("/")[0]));
                    player.setOverall(Integer.parseInt(element.child(10).html()));

                    return player;
                })
                .filter(filter)
                .collect(Collectors.toList());
    }
}
