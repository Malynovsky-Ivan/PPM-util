package com.malynovsky.restapp.resource;

import com.malynovsky.restapp.entity.Player;
import com.malynovsky.restapp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/players")
public class PlayerResource {

    @Autowired
    private PlayerService playerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> getAll() {
        return playerService.getAll();
    }

    @GET
    @Path("/age")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> getAllByAge(@QueryParam("min") Integer min, @QueryParam("max") Integer max) {
        return playerService.getAllByAge(min, max).stream()
                .sorted((o1, o2) -> -Integer.compare(o1.getOverall(), o2.getOverall())).collect(Collectors.toList());
    }

    @GET
    @Path("/top")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> getAllTop() {
        List<Player> result = playerService.getAllTop();
        return result;
    }
}
