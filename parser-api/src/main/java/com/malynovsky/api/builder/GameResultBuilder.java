package com.malynovsky.api.builder;

import com.malynovsky.api.entity.GamePoint;
import com.malynovsky.api.entity.GameResult;

/**
 * Created by Ivan on 29.05.2019 - 23:32.
 * ppm-telegram-bot
 */
public class GameResultBuilder extends AbstractBuilder<GameResult> {

    public GameResultBuilder() {
        super(new GameResult());
    }

    GameResultBuilder update(GamePoint point) {
        game.updateScores(point.getPoints(), point.getTeam());
        return this;
    }

    public GameResultBuilder addPoint(GamePoint points) {
        game.updateScores(points.getPoints(), points.getTeam());
        return this;
    }
}
