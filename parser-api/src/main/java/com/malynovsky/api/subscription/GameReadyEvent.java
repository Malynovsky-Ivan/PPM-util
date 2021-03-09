package com.malynovsky.api.subscription;

import java.time.LocalTime;

/**
 * Created by Ivan on 31.05.2019 - 22:30.
 * ppm-telegram-bot
 */
public class GameReadyEvent implements Event<LocalTime> {

    private LocalTime gameTime;

    public void setGameTime(LocalTime gameTime) {
        this.gameTime = gameTime;
    }

    @Override
    public EventType getEventType() {
        return EventType.GAME_IS_CALCULATED;
    }

    @Override
    public LocalTime getEventInfo() {
        return gameTime;
    }
}
