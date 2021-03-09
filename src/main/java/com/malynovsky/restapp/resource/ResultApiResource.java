package com.malynovsky.restapp.resource;

import com.malynovsky.restapp.api.ResultService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/result")
public class ResultApiResource {

    @Autowired
    private ResultService resultService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getSimpleResult(@QueryParam("matchId") String matchId) {
        return resultService.returnSimpleResult(matchId);
    }
}
