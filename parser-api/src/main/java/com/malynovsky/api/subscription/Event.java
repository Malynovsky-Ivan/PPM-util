package com.malynovsky.api.subscription;

/**
 * Created by Ivan on 31.05.2019 - 21:59.
 * ppm-telegram-bot
 */
public interface Event<T> {

    EventType getEventType();

    T getEventInfo();
}
