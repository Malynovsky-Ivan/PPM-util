package com.malynovsky.restapp.api.impl;

import com.malynovsky.api.parsers.SimpleParser;
import com.malynovsky.restapp.api.ResultService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ResultServiceImpl implements ResultService {

    @Override
    public String returnSimpleResult(String matchId) {


        try {
            return new SimpleParser().parse(matchId);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
