package com.nexio.autoball.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BsArray{
    public int nBallCount;
    @JsonProperty("BallCode")
    public List<BallCode> ballCode;

    public int getnBallCount() {
        return nBallCount;
    }

    public void setnBallCount(int nBallCount) {
        this.nBallCount = nBallCount;
    }

    public List<BallCode> getBallCode() {
        return ballCode;
    }

    public void setBallCode(List<BallCode> ballCode) {
        this.ballCode = ballCode;
    }
}