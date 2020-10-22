package com.nexio.autoball.model;

import org.jdbi.v3.json.Json;

import java.util.Map;
import java.util.TreeMap;

public class Draw {

    //SmallJackPot
    //YeeKee
    DrawType gameId;

    //期號yyMMddHHmm
    String gameNum;

    @Json
    Map<String,String> balls;

    public DrawType getGameId() {
        return gameId;
    }

    public void setGameId(DrawType gameId) {
        this.gameId = gameId;
    }

    public String getGameNum() {
        return gameNum;
    }

    public void setGameNum(String gameNum) {
        this.gameNum = gameNum;
    }

    public Map<String, String> getBalls() {
        return balls;
    }

    public void setBalls(Map<String, String> balls) {
        this.balls = balls;
    }


}
