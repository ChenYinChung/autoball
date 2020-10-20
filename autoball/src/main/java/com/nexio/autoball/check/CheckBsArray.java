package com.nexio.autoball.check;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CheckBsArray {
    public int nBallCount;
    @JsonProperty("BallCode")
    public List<String> ballCode;
}
