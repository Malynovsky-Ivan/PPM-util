package com.malynovsky.restapp.resource;

import com.malynovsky.api.dto.TeamAggregatedStatistic;
import com.malynovsky.restapp.entity.Team;
import com.malynovsky.restapp.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static java.lang.Integer.compare;

@Path("/teams")
public class TeamResource {

    @Autowired
    private TeamService teamService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Team> getAll() {
        return teamService.getAll();
    }

    @GET
    @Path("/{parameter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Team getTeam(@PathParam("parameter") String parameter) {
        try {
            return teamService.getById(Integer.parseInt(parameter));
        } catch (NumberFormatException exception) {
            return teamService.getByName(parameter);
        }
    }

    @GET
    @Path("/historyPlaceTable")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TeamAggregatedStatistic> getHistoryPlaceTable(@QueryParam("country") String country) {
        return teamService.generatePositionStatistic(country);
    }

    @GET
    @Path("/historyRegularNumbers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TeamAggregatedStatistic> getRegularSeasonNumber(@QueryParam("country") String country,
                                                                @QueryParam("sorting") String sorting) {
        List<TeamAggregatedStatistic> result = teamService.generateRegularPositionStatistic(country);

        if (sorting != null) { //TODO
            result.sort((TeamAggregatedStatistic o1, TeamAggregatedStatistic o2) -> -compare(o1.getMatchesWon(),
                    o2.getMatchesWon()));
        }

        return result;
    }
}
