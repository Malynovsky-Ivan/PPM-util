package com.malynovsky.api.parsers;

import com.malynovsky.api.builder.AbstractBuilder;
import com.malynovsky.api.entity.Game;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by Ivan on 31.05.2019 - 22:14.
 * ppm-telegram-bot
 */
public interface GameParser<GAME extends Game, BUILDER extends AbstractBuilder> extends Parser<GAME> {

    List<String> parseTeams(Document document);
}
