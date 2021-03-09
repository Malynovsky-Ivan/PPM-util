package com.malynovsky.api.parsers.statistic;

import com.malynovsky.api.dto.CountryStatistic;
import com.malynovsky.api.parsers.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class WorldCupStatisticParser implements Parser<List<CountryStatistic>> {
    @Override
    public String parse(String url) throws IOException {
        return null;
    }

    @Override
    public List<CountryStatistic> parse(Document document) throws IOException {
        return document.select("tbody").get(0).children()
                .stream()
                .map(element -> {
                    CountryStatistic statistic = new CountryStatistic();
                    statistic.setPlace(Integer.parseInt(element.child(0).html()));

                    Element child = element.child(1).child(1);
                    statistic.setCountryName(child.html());
                    return statistic;
                })
                .collect(Collectors.toList());
    }
}
