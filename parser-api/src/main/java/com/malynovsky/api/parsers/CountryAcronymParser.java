package com.malynovsky.api.parsers;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CountryAcronymParser implements Parser<List<String>> {
    @Override
    public String parse(String url) throws IOException {
        return null;
    }

    @Override
    public List<String> parse(Document document) throws IOException {
        return document.select("select").stream()
                .filter(element -> element.hasAttr("name") && element.attr("name").equals("country"))
                .flatMap(element -> element.children().stream())
                .map(element -> element.attr("value"))
                .collect(Collectors.toList());
    }
}
