package com.nexio.autoball.entity;

import com.nexio.autoball.model.GameInfo;

public class Draw {
    String gameNum;
    GameInfo drawResult;
    int drawStatus;

    public String getGameNum() {
        return gameNum;
    }

    public void setGameNum(String gameNum) {
        this.gameNum = gameNum;
    }

    public GameInfo getDrawResult() {
        return drawResult;
    }

    public void setDrawResult(GameInfo drawResult) {
        this.drawResult = drawResult;
    }

    public int getDrawStatus() {
        return drawStatus;
    }

    public void setDrawStatus(int drawStatus) {
        this.drawStatus = drawStatus;
    }
}
