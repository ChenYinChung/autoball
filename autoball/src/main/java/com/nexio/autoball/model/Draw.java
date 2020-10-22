package com.nexio.autoball.model;

import org.jdbi.v3.json.Json;

public class Draw {
    String gameId;

    String gameNum;
    @Json
    GameInfo gameInfo;
    int drawStatus;

    public String getGameNum() {
        return gameNum;
    }

    public void setGameNum(String gameNum) {
        this.gameNum = gameNum;
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public int getDrawStatus() {
        return drawStatus;
    }

    public void setDrawStatus(int drawStatus) {
        this.drawStatus = drawStatus;
    }
}
