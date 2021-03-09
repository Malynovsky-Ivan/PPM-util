package com.malynovsky.api.parsers;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TeamNamesParser implements Parser<List<String>> {
    @Override
    public String parse(String url) throws IOException {
        return null;
    }

    @Override
    public List<String> parse(Document document) throws IOException, NoSuchLeagueException {
        try {
            return document.select("tbody").get(0).children()
                    .stream()
                    .map(element -> element.child(1).child(1).attr("href"))
                    .collect(Collectors.toList());
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchLeagueException();
        }
    }

    public class NoSuchLeagueException extends IndexOutOfBoundsException {

        @Override
        public String getMessage() {
            return "There is no such league in this country.";
        }
    }
}
