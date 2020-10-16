package com.nexio.autoball.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexio.autoball.component.SocketClient;
import com.nexio.autoball.model.BallCode;
import com.nexio.autoball.model.BsArray;
import com.nexio.autoball.model.GameInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
@EnableAsync
public class SchedulerService {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerService.class);

    static int CUR_GAME_NUM = 100;
    static final int GAME_COUNT = 1;
    static final int TIME_SPAM = 0;
    @Autowired
    SocketClient socketClient;

    //每隔5秒执行一次
    @Async
//    @Scheduled(fixedRate = 120000)
//    @Scheduled(cron = "0 7,17,27,37,47,57 * * * *")
    @Scheduled(cron = "7,17,27,37,47,57 * * * * *")
    public void testTasks() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("startGame7").append(",").append(CUR_GAME_NUM).append(",").append(GAME_COUNT).append(",").append(TIME_SPAM);

            if (socketClient==null)
                return;

            String json = socketClient.send(sb.toString());
            logger.info("Run in testTasks {}", json);
//            showGameInfo(json);

            CUR_GAME_NUM++;
        } catch (IOException e) {
            logger.error("Task error", e);
        }

    }

    //0,20,40 每20分執行一次
    @Async
//    @Scheduled(cron = "0 0,20,40 * * * *")
    @Scheduled(cron = "1,11,21,31,41,51 * * * * *")
    public void testTasks2() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("startGame1").append(",").append(CUR_GAME_NUM).append(",").append(GAME_COUNT).append(",").append(TIME_SPAM);

            if (socketClient==null)
                return;

            String json = socketClient.send(sb.toString());
            logger.info("Run in testTasks2 {}", json);

            CUR_GAME_NUM++;
        } catch (IOException e) {
            logger.error("Task error", e);
        }
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
