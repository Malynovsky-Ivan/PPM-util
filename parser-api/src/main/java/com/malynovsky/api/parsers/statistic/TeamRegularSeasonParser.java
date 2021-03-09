package com.malynovsky.api.parsers.statistic;

import com.malynovsky.api.dto.TeamAggregatedStatistic;
import com.malynovsky.api.parsers.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeamRegularSeasonParser implements Parser<List<TeamAggregatedStatistic>> {

    @Override
    public String parse(String url) throws IOException {
        return null;
    }

    @Override
    public List<TeamAggregatedStatistic> parse(Document document) throws IOException {
        var teams = new ArrayList<TeamAggregatedStatistic>();

        IntHolder position = new IntHolder();
        document.select("tbody").get(0).children()
                .stream()
                .map(element -> {
                    String teamName = element.child(1).child(1).html();
                    TeamAggregatedStatistic teamAggregatedStatistic = new TeamAggregatedStatistic(teamName, position.getIndex());
                    String[] points = clear(element.child(6).html()).split(":");
                    teamAggregatedStatistic
                            .update(convertToInt(element,2), convertToInt(element,3),
                                    Integer.valueOf(points[0]), Integer.valueOf(points[1]));
                    position.increment();

                    return teamAggregatedStatistic;
                }).forEach(teams::add);

        return teams;
    }

    private String clear(String value) {
        return value.replace("&nbsp;", "");
    }

    private int convertToInt(Element element, int index) {
        return Integer.valueOf(clear(element.child(index).html()));
    }

    private class IntHolder {
        private int index = 1;

        public void increment() {
            index++;
        }

        public int getIndex() {
            return index;
        }
    }
}
