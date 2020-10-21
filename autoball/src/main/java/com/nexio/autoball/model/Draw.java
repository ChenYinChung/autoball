package com.nexio.autoball.model;

import com.nexio.autoball.model.GameInfo;
import org.jdbi.v3.json.Json;

public class Draw {
    String gameNum;
    @Json
    String drawResult;
    int drawStatus;

    public String getGameNum() {
        return gameNum;
    }

    public void setGameNum(String gameNum) {
        this.gameNum = gameNum;
    }

    public String getDrawResult() {
        return drawResult;
    }

    public void setDrawResult(String drawResult) {
        this.drawResult = drawResult;
    }

    public int getDrawStatus() {
        return drawStatus;
    }

    public void setDrawStatus(int drawStatus) {
        this.drawStatus = drawStatus;
    }
}
