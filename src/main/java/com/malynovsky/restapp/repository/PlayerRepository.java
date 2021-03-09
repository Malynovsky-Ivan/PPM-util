package com.malynovsky.restapp.repository;

import com.malynovsky.restapp.entity.Player;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    List<Player> getAllByAgeBetween(Integer mainAge, Integer maxAge);

    //List<Player> g(Pageable pageConfig);
}
