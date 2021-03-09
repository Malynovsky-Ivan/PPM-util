package com.malynovsky.api.parsers;

import com.malynovsky.api.dto.GameDto;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.LocalDate;
import java.util.stream.Stream;

public class GameFinderParser implements Parser<GameDto> {

    @Override
    public String parse(String url) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public GameDto parse(Document document) throws IOException {
        GameDto game = null;

        LocalDate today = LocalDate.now();
//        Long gameId = document.select("a")
//                .stream()
//                .filter(element -> element.hasAttr("href"))
//                .filter(element -> element.attr("href").contains("basketball-broadcast"))
//                .map(element -> element.attr("href").split("=")[1])
//                .map(Long::parseLong)
//                .findFirst()
//                .orElse(null);
        Long gameId = document.select("a")
                .stream()
                .filter(element -> element.hasAttr("href"))
                .filter(element -> element.attr("href").contains("add_to_schedule"))
                .flatMap(element -> Stream.of(element.attr("href").split("&")))
                .filter(s -> s.contains("id_match"))
                .map(s -> s.split("=")[1])
                .map(Long::parseLong)
                .findFirst()
                .orElse(null);
        if (gameId != null) {
            game = new GameDto();
            game.setId(gameId);
        }

        return game;
    }
}
