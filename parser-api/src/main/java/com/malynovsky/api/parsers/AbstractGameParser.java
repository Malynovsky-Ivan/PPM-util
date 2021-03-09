package com.malynovsky.api.parsers;

import com.malynovsky.api.builder.GameResultBuilder;
import com.malynovsky.api.entity.GameMoment;
import com.malynovsky.api.entity.GamePoint;
import com.malynovsky.api.entity.GameResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ivan on 28.05.2019 - 21:29.
 * ppm-telegram-bot
 */
public class AbstractGameParser implements GameParser<GameResult, GameResultBuilder> {
    private static Pattern teamPattern = Pattern.compile("stats_action\\[(\\d+)]\\['team'] = (\\d)");
    private static Pattern pointPattern = Pattern.compile("stats_action\\[(\\d+)]\\['goal_(\\d)'] = (\\d)");

    private GameResultBuilder builder;

    public AbstractGameParser() {
        builder = new GameResultBuilder();
    }

    public String parse(String url) throws IOException {
        Document gameDocument = Jsoup.connect(url).get();
        String scriptElementText = getGameText(gameDocument).html();

        builder.setTeams(parseTeams(gameDocument));

        Matcher teamMatcher = teamPattern.matcher(scriptElementText);
        Matcher pointMatcher = pointPattern.matcher(scriptElementText);

        System.out.println("Already scored: " + pointMatcher.find());
        GamePoint scored = getScoredPoint(pointMatcher);

        while (teamMatcher.find()) {
            GameMoment moment = getPoint(teamMatcher);
            if (scored.before(moment) && pointMatcher.find()) {
                scored = getScoredPoint(pointMatcher);
            }

            if (scored.equals(moment)) {
                scored.setTeam(moment.getTeam());
                builder.addPoint(scored);
                if (pointMatcher.find()) {
                    scored = checkForAndOne(pointMatcher, moment);
                }
                scored.setTeam(null);
            }
        }

        return builder.build().getResult();
    }

    @Override
    public GameResult parse(Document document) throws IOException {
        return null;
    }

    @Override
    public List<String> parseTeams(Document document) {
        return document.body()
                .getElementsByClass("name_team")
                .eachText();
    }

    public Element getGameText(Document document) {
        return document.select("script")
                .stream()
                .filter(element -> !element.hasAttr("language"))
                .max(Comparator.comparing(Element::html))
                .orElseThrow(RuntimeException::new);
    }

    private GamePoint getScoredPoint(Matcher matcher) {
        int secondsInGame = Integer.parseInt(matcher.group(1));
        int points = Integer.parseInt(matcher.group(2)) * Integer.parseInt(matcher.group(3));

        return new GamePoint(secondsInGame, points);
    }

    private GamePoint checkForAndOne(Matcher pointMatcher, GameMoment moment) {
        GamePoint scored;
        scored = getScoredPoint(pointMatcher);
        scored.setTeam(moment.getTeam());
        if (scored.equals(moment)) {
            builder.addPoint(scored);
        }
        return scored;
    }

    private GameMoment getPoint(Matcher matcher) {
        int secondsInGame = Integer.parseInt(matcher.group(1));
        String team = matcher.group(2);

        return new GamePoint(secondsInGame, team);
    }
}
