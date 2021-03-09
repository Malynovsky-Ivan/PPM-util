package com.malynovsky.api.parsers;

import com.malynovsky.api.constant.Constants;
import com.malynovsky.api.subscription.Event;
import com.malynovsky.api.subscription.GameReadyEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by Ivan on 31.05.2019 - 22:07.
 * ppm-telegram-bot
 */
public class EventParser implements Parser<Event> {

    @Override
    public String parse(String url) throws IOException {
        if (url != null && !url.isEmpty()) {
            if (!url.toLowerCase().startsWith("http") && !url.toLowerCase().startsWith("http")) {
                url = Constants.CALENDAR_LINK + url;
            }
        }

        return parse(Jsoup.connect(url).get()).toString();
    }

    @Override
    public Event parse(Document document) throws IOException {
        if (document.select("script").size() > 1) {
            return new GameReadyEvent();
        }

        return null;
    }
}
