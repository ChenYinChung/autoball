package com.nexio.autoball.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexio.autoball.entity.Draw;
import com.nexio.autoball.model.BallCode;
import com.nexio.autoball.model.BsArray;
import com.nexio.autoball.model.GameInfo;
import com.nexio.autoball.repo.DrawRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 由AutoBall
 */
@Service
public class DrawService {
    private static final Logger logger = LoggerFactory.getLogger(DrawService.class);

    @Autowired
    DrawRepo drawRepo;

    public void draw(String gameNum, String drawResult) {
        GameInfo gameInfo = parse(gameNum,drawResult);

        logger.info("開獎期號　gameNum src[{}]",gameNum);
        logger.info("開獎結果　drawResult src[{}]",drawResult);

        ObjectMapper objectMapper =  new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(gameInfo);
        } catch (JsonProcessingException e) {
            logger.error("gameInfo error[{}]",e);
        }
        logger.info("gameInfo json[{}]",json);


        Draw draw =  drawRepo.findByGameNum(gameNum);
        draw.setDrawResult(gameInfo);
        draw.setDrawStatus(1);
        drawRepo.update(draw);
    }

    private GameInfo parse(String gameNum, String drawResult){

        logger.info("GameNum[{}] drawNums[{}]", gameNum, drawResult);
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

    public static void main(String[] arg) {
        DrawService drawService = new DrawService();
//        drawService.draw("201019001", "1,2,6_1,2,3;2,3,4;1,5,6");
        drawService.draw("2010211445", "1,2,3,4,5_8;0;ABCD;4;Q");
    }
}
