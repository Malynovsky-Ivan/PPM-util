package com.malynovsky.restapp.resource;

import com.malynovsky.restapp.api.CountryService;
import com.malynovsky.restapp.entity.Player;
import com.malynovsky.restapp.reporting.ReportService;
import com.malynovsky.restapp.service.PlayerService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Path("/reports")
public class ReportResource {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ReportService reportService;

    @GET
    @Produces("application/vnd.ms-excel")
    @Path("/{age}")
    public Response getForNationalTeam(@PathParam("age") Integer age) {
        int min = 15;
        int max = 17;
        if (age != null && age > 17) {
            min = 18;
            max = 19;
        }
        List<Player> result = playerService.getAllByAge(min, max).stream()
                .sorted((o1, o2) -> {
                    int resultOfComparing = -Integer.compare(o1.getOverall(), o2.getOverall());

                    return resultOfComparing == 0 ? Integer.compare(o1.getAge(), o2.getAge()) : resultOfComparing;
                }).collect(Collectors.toList());

        try {
            String fileName = "report for " + min + "-" + max + " years.xls"; //TODO temporary folder
            FileOutputStream outputStream = new FileOutputStream(fileName);
            Workbook workbook = reportService.buildExcel(result);
            workbook.write(outputStream);
            workbook.close();

            Response.ResponseBuilder response = Response.ok(outputStream);
            response.header("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

            return response.build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Produces("application/vnd.ms-excel")
    @Path("/best")
    public Response getAllBest(@QueryParam("limit") Integer limit) {
//        Map<Integer, List<? extends Reportable>> resultMap = new HashMap<>();
//        playerService.getAll().getAllByAgeForAll().forEach((key, value) -> resultMap.put(key, //TODO
//                value.stream().sorted().limit(limit == null ? 20 : limit)
//                        .map(Player::new).collect(Collectors.toList()))); //TODO limit
//
//        try {
//            String fileName = "report for top-20 by years.xls"; //TODO temporary folder
//            FileOutputStream outputStream = new FileOutputStream(fileName);
//            Workbook workbook = reportService.buildExcel(resultMap);
//            workbook.write(outputStream);
//            workbook.close();
//
//            Response.ResponseBuilder response = Response.ok(outputStream);
//            response.header("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//
//            return response.build();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

}
