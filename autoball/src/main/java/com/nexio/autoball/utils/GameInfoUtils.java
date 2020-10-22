package com.nexio.autoball.utils;

import com.nexio.autoball.model.BallCode;
import com.nexio.autoball.model.BsArray;
import com.nexio.autoball.model.GameInfo;

import java.util.ArrayList;
import java.util.List;

public class GameInfoUtils {
    public static  GameInfo parse(String gameNum, String drawResult){

        String[] drawSrc = drawResult.split("_");
        //有幾個桶
        String[] drawArraysSrc = drawSrc[0].split(",");

        //每個桶內容
        String[] bsArraySrc = drawSrc[1].split(";");

        GameInfo gameInfo = new GameInfo();
        gameInfo.nGameNum = Integer.parseInt(gameNum);
        gameInfo.dwGameTime = System.currentTimeMillis();

        List<BsArray> bsArrayList = new ArrayList<>();

        for (int i = 0; i < bsArraySrc.length; i++) {
            BsArray bsArray = new BsArray();
            bsArrayList.add(bsArray);

            String[] bcArrays = bsArraySrc[i].split(",");

            List<String> balls = new ArrayList<>();
            for (int j= 0 ; j < bcArrays.length;j++){
                balls.add(bcArrays[j]);
            }
            BallCode ballCode = new BallCode();
            ballCode.code = balls;

            List<BallCode> bcArrayList = new ArrayList<>();
            bcArrayList.add(ballCode);
            bsArray.nBallCount = bcArrayList.size();
            bsArray.ballCode = bcArrayList;
        }
        gameInfo.bsArray = bsArrayList;

        return gameInfo;
    }

}
