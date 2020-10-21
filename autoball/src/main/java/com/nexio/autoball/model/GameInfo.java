package com.nexio.autoball.model;

import java.util.List;

public class GameInfo {
    public int nGameNum;
    public long dwGameTime;
    public List<BsArray> bsArray;

    public int getnGameNum() {
        return nGameNum;
    }

    public void setnGameNum(int nGameNum) {
        this.nGameNum = nGameNum;
    }

    public long getDwGameTime() {
        return dwGameTime;
    }

    public void setDwGameTime(long dwGameTime) {
        this.dwGameTime = dwGameTime;
    }

    public List<BsArray> getBsArray() {
        return bsArray;
    }

    public void setBsArray(List<BsArray> bsArray) {
        this.bsArray = bsArray;
    }
}
