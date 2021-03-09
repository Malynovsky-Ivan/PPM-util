package com.malynovsky.api.parsers.statistic;

import com.malynovsky.api.dto.TeamAggregatedStatistic;
import com.malynovsky.api.dto.TeamCupStatistic;
import com.malynovsky.api.parsers.Parser;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InternationalCupParser  implements Parser<List<TeamCupStatistic>> {


    @Override
    public String parse(String url) throws IOException {
        return null;
    }

    @Override
    public List<TeamCupStatistic> parse(Document document) throws IOException {
        var teams = new ArrayList<TeamCupStatistic>();

        return null;
    }
}
