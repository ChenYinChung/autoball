package com.nexio.autoball.model;

import org.jdbi.v3.json.Json;

import java.util.Date;
import java.util.Map;

public class Draw {

    //SmallJackPot
    //YeeKee
    DrawType gameId;

    //期號yyMMddHHmm
    String gameNum;

    @Json
    Map<String,String> balls;

    Date createDate;

    Date updateDate;


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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
