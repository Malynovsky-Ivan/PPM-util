package com.malynovsky.api.parsers.statistic;

import com.malynovsky.api.parsers.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class TeamPositionParser implements Parser<Set<String>> {

    @Deprecated
    @Override
    public String parse(String url) throws IOException {
        return null;
    }

    @Override
    public Set<String> parse(Document document) {
        var result = new LinkedHashSet<String>();

        document.select("td")
                .stream()
                .filter(element -> element.hasAttr("width"))
                .filter(element -> element.attr("width").equals("60%"))
                .map(element -> element.child(1))
                .map(Element::html)
                .forEach(result::add);

        return result;
    }
}
