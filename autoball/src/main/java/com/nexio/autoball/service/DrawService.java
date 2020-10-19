package com.nexio.autoball.service;

import com.nexio.autoball.model.BsArray;
import com.nexio.autoball.model.GameInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DrawService {
    private static final Logger logger = LoggerFactory.getLogger(DrawService.class);

    public void draw(String gameNum, String drawResult) {

        logger.info("GameNum[{}] drawNums[{}]",gameNum,drawResult);
        //開獎的桶號有幾個,以及號碼==> 1,2,6_1,2,3;2,3,4;1,5,6


        String[] drawSrc = drawResult.split("_");
        //有幾個桶
        String[] drawArraysSrc = drawSrc[0].split(",");

        //每個桶內容
        String[] bsArraySrc = drawSrc[1].split(";");

        GameInfo gameInfo = new GameInfo();
        gameInfo.nGameNum = Integer.parseInt(gameNum);
        gameInfo.dwGameTime = System.currentTimeMillis();

        List<BsArray> bsArrayList = new ArrayList<>();

        for(int i=0 ; i< bsArraySrc.length;i++){
            BsArray bsArray = new BsArray();
            bsArrayList.add(bsArray);
        }
        gameInfo.bsArray = bsArrayList;

    }


    public static void main(String[] arg){
        DrawService drawService = new DrawService();
        drawService.draw("201019001","1,2,6_1,2,3;2,3,4;1,5,6");
    }
}
