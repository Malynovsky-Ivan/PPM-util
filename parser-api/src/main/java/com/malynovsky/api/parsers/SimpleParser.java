package com.malynovsky.api.parsers;

import com.malynovsky.api.builder.GameResultBuilder;
import com.malynovsky.api.constant.Constants;
import com.malynovsky.api.entity.Game;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public class SimpleParser implements GameParser<Game, GameResultBuilder> {
    private GameParser parser;

    public SimpleParser() {
        parser = new AbstractGameParser();
    }

    @Override
    public List<String> parseTeams(Document document) {
        return null;
    }

    @Override
    public String parse(String id) throws IOException {
        return parser.parse(Constants.GAME_PREFFIX + id);
    }

    @Override
    public Game parse(Document document) throws IOException {
        return null;
    }
}
