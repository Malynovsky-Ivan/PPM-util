package com.malynovsky.api.parsers;

import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by Ivan on 28.05.2019 - 21:29.
 * ppm-telegram-bot
 */
public interface Parser<T> {

    @Deprecated
    String parse(String url) throws IOException; // TODO generic instead of String

    T parse(Document document) throws IOException;
}
