package com.malynovsky.restapp.repository;

import com.malynovsky.restapp.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    Team getTeamByName(String name);
}
