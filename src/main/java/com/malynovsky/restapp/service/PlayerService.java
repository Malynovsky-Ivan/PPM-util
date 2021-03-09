package com.malynovsky.restapp.service;

import com.malynovsky.restapp.entity.Player;

import java.util.List;

public interface PlayerService extends Service<Player> {

    List<Player> getAllByAge(Integer min, Integer max);

    List<Player> getAllTop();
}
