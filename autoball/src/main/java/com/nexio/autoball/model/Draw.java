package com.nexio.autoball.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    Date createtDate;
    Date updateDate;

//    @JsonIgnore
//    boolean retry;
//    @JsonIgnore
//    int retryCount;
//    @JsonIgnore
//    int status;

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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreatetDate() {
        return createtDate;
    }

    public void setCreatetDate(Date createtDate) {
        this.createtDate = createtDate;
    }

//    public boolean isRetry() {
//        return retry;
//    }
//
//    public void setRetry(boolean retry) {
//        this.retry = retry;
//    }
//
//    public int getRetryCount() {
//        return retryCount;
//    }
//
//    public void setRetryCount(int retryCount) {
//        this.retryCount = retryCount;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
}
