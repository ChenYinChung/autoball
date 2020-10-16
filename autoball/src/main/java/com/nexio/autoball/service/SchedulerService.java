package com.nexio.autoball.service;

import com.nexio.autoball.component.SocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
    @Scheduled(fixedRate = 5000)
    public void testTasks() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("startGame").append(",").append(CUR_GAME_NUM).append(",").append(GAME_COUNT).append(",").append(TIME_SPAM);

            String json = socketClient.send(sb.toString());
            logger.info("Run in testTasks {}", json);

            CUR_GAME_NUM++;
        } catch (IOException e) {
            logger.error("Task error", e);
        }

    }

    //0,20,40 每20分執行一次
    @Async
    @Scheduled(cron = "0 0,20,40 * * * *")
    public void testTasks2() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("startGame").append(",").append(CUR_GAME_NUM).append(",").append(GAME_COUNT).append(",").append(TIME_SPAM);

            String json = socketClient.send(sb.toString());
            logger.info("Run in testTasks2 {}", json);

            CUR_GAME_NUM++;
        } catch (IOException e) {
            logger.error("Task error", e);
        }
    }
}
