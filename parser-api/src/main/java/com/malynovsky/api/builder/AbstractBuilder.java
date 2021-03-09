package com.malynovsky.api.builder;

import com.malynovsky.api.entity.Game;

import java.util.List;

/**
 * Created by Ivan on 29.05.2019 - 21:55.
 * ppm-telegram-bot
 */
public abstract class AbstractBuilder<R extends Game> {
    protected R game;

    protected AbstractBuilder(R game) {
        this.game = game;
    }

    public AbstractBuilder setTeams(List<String> teamNames) {
        game.setTeams(teamNames.get(0), teamNames.get(1));
        return this;
    }

    public R build() {
        return game;
    }
}
