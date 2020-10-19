package com.nexio.autoball.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexio.autoball.component.SocketClient;
import com.nexio.autoball.model.BallCode;
import com.nexio.autoball.model.BsArray;
import com.nexio.autoball.model.GameInfo;
import com.nexio.sunzing.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Component
@EnableAsync
public class SchedulerService {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerService.class);

    @Autowired
    SocketClient socketClient;

    /**
     * 只開前五個管
     */
    @Async
    @Scheduled(cron = "0 0,10,20,30,40,50 * * * *")
    public void draw5Balls() {
        try {
            String requset = "ant,1,1,1,1,1,0";
            String json = socketClient.send(requset);
            Thread.sleep(5000);

            requset = "startGame,"+getDataIssue()+",1,0";
            json = socketClient.send(requset);

            logger.info("Run in draw5Balls {}", json);
        } catch (Exception e) {
            logger.error("Task error", e);
        }

    }

    /**
     * 只開最後一管
     */
    @Async
    @Scheduled(cron = "0 5,15,25,35,45,55 * * * *")
    public void drawSingleBalls() {
        try {
            String requset = "ant,0,0,0,0,0,1";
            String json = socketClient.send(requset);
            Thread.sleep(5000);

            requset = "startGame,"+getDataIssue()+",1,0";
            json = socketClient.send(requset);

            logger.info("Run in drawSingleBalls {}", json);
        } catch (Exception e) {
            logger.error("Task error", e);
        }

    }

    private String getDataIssue(){
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyMMddHHmm");
        Date current = new Date();
        String fmt = sdFormat.format(current);
        return fmt;
    }

    private void showGameInfo(String json) throws JsonProcessingException {
        ObjectMapper objectMapper =  new ObjectMapper();
        GameInfo g = objectMapper.readValue(json,GameInfo.class);
        ArrayList<String> list = new ArrayList<>();
        for(BsArray bsarry:g.bsArray){
            for(BallCode ballCode:bsarry.ballCode){
                StringBuilder sb = new StringBuilder();
                for (String ball:ballCode.code){
                    sb.append(ball);
                }
                list.add(sb.toString());
            }
        }
        logger.info("ball={}", list);

    }
}
