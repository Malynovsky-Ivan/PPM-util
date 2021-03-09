package com.malynovsky.restapp.resource;

import com.malynovsky.api.entity.Player;
import com.malynovsky.api.filter.PlayerFilter;
import com.malynovsky.restapp.api.CountryService;
import com.malynovsky.restapp.dto.CountryWorldCupHistory;
import com.malynovsky.restapp.util.FilterFactory;
import com.malynovsky.restapp.util.WorldCupFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/country")
public class CountryResource {

    @Autowired
    private CountryService countryService;

    @GET
    @Path("/players/{parameter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Integer, List<Player>> getAllPlayersByAge(@PathParam("parameter") String parameter) {

        return sortAndMap(countryService.getAllByAgeInLeague(parameter, null), 20);
    }

    @GET
    @Path("/players/best")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Integer, List<Player>> getAllPlayersByAgeForAll() {

        return sortAndMap(countryService.getAllByAgeForAll(), 20);
    }

    @GET
    @Path("/players/country/best")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Integer, List<Player>> getAllPlayersByAgeForCountry(
            @QueryParam("country") String countryName,
            @QueryParam("maxAge") Integer maxAge,
            @QueryParam("minAge") Integer minAge) {

        PlayerFilter.FilterBuilder builder = new PlayerFilter.FilterBuilder();

        if (maxAge != null) {
            builder.maxAge(maxAge);
        }
        if (minAge != null) {
            builder.minAge(minAge);
        }
        if (countryName != null) {
            builder.country(countryName);
        }

        return sortAndMap(countryService.getAllByAgeForCountry(builder.build()), 40); //TODO limit should also be configurable
    }

    @GET
    @Path("/{sport}/worldCup/best")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CountryWorldCupHistory> getAllPlayersByAgeForCountry(@PathParam("sport") String sport,
                                                                     @QueryParam("param") Integer param) {

        WorldCupFilter filter = FilterFactory.getFilter(sport, param);

        List<CountryWorldCupHistory> result = countryService.getHistoryByCompetition(filter);

        return result.stream().sorted().collect(Collectors.toList());
    }

    private Map<Integer, List<Player>> sortAndMap(Map<Integer, List<Player>> allByAgeForAll, int limit) {
        var result = new HashMap<Integer, List<Player>>();
        allByAgeForAll
                .forEach((integer, players) -> result
                        .put(integer, players.stream().sorted().limit(limit).collect(Collectors.toList())));

        return result;
    }
}
