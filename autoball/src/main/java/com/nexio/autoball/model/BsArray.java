package com.nexio.autoball.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BsArray{
    public int nBallCount;
    @JsonProperty("BallCode")
    public List<BallCode> ballCode;
}