package com.nexio.autoball.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexio.autoball.check.CheckGameInfo;
import com.nexio.autoball.component.SocketClient;
import com.nexio.autoball.model.Draw;
import com.nexio.autoball.repo.DrawRepo;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@EnableAsync
public class SchedulerService {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerService.class);

    @Autowired
    SocketClient socketClient;

    @Value("${draw.path}")
    String drawPath;

    @Autowired
    DrawRepo drawRepo;

    /**
     * 只開前五個管
     * 每十分鐘一次
     */
    @Async
    @Scheduled(cron = "0 0,10,20,30,40,50 * * * *")
    public void draw5Balls() {
        try {
            String requset = "ant,1,1,1,1,1,1";
            String json = socketClient.send(requset);
            logger.info("自動排程－設定前五管{}", json);
            Thread.sleep(5000);

            String issue = getIssue();
            requset = "startGame," + issue + ",1,0";
            json = socketClient.send(requset);
            logger.info("自動排程－前五管開始啟動{}", json);

            insertDraw(issue);

            logger.info("Run in draw5Balls {}", json);
        } catch (Exception e) {
            logger.error("Task error", e);
        }
    }

    /**
     * 只開最後一管
     * 每五分鐘一次
     */
    @Async
    @Scheduled(cron = "0 5,15,25,35,45,55 * * * *")
    public void drawSingleBalls() {
        try {
            String requset = "ant,1,1,1,1,1,0";
            String json = socketClient.send(requset);
            logger.info("自動排程－設定第六管{}", json);
            Thread.sleep(5000);
            String issue = getIssue();

            requset = "startGame," + issue + ",1,0";
            json = socketClient.send(requset);
            insertDraw(issue);
            logger.info("自動排程－第六管開始啟動{}", json);
            logger.info("Run in drawSingleBalls {}", json);

        } catch (Exception e) {
            logger.error("Task error", e);
        }
    }

    /**
     * 檢核DB&檔案結果
     */
    @Async
    @Scheduled(cron = "0 2,7,12,17,22,27,32,37,42,47,52,57 * * * *")
    public void checkBalls() {
        try {

            Date rightNow = new Date();   //當前時間
            Calendar calendar = Calendar.getInstance(); //得到日曆
            calendar.setTime(rightNow);//把當前時間賦給日曆
            File test = new File(checkIssue(rightNow));
            List<String> jsons = FileUtils.readLines(test, Charset.forName("UTF-8"));

            for(String json : jsons){
                ObjectMapper objectMapper =  new ObjectMapper();
                CheckGameInfo gameInfo = objectMapper.readValue(json, CheckGameInfo.class);
                // if check in db status in running ,apply
                // call DrawService call back to SLE-CMS service
                //logger.info("自動排程－檢核檔案開獎結果 {}", json);

                Draw draw = drawRepo.findByGameNum(""+gameInfo.nGameNum);

                if(draw==null){
                    logger.error("Draw game num not found[{}]",gameInfo.nGameNum);
                }else{
                    logger.error("Draw game num json[{}]",draw.getDrawResult());
                }

            }
        } catch (Exception e) {
            logger.error("Task error", e);
        }

    }


    private String getIssue() {
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyMMddHHmm");
        Date current = new Date();
        String fmt = sdFormat.format(current);
        return fmt;
    }
    private String checkIssue(Date date) {
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
        String fmt = sdFormat.format(date);
        return drawPath+"/Ball"+fmt+".txt";
    }

    private void insertDraw(String issue){
        Draw draw = new Draw();
        draw.setGameNum(issue);
        draw.setDrawStatus(0);
        drawRepo.insert(draw);
    }

//
//    private void showGameInfo(String json) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        GameInfo g = objectMapper.readValue(json, GameInfo.class);
//        ArrayList<String> list = new ArrayList<>();
//        for (BsArray bsarry : g.bsArray) {
//            for (BallCode ballCode : bsarry.ballCode) {
//                StringBuilder sb = new StringBuilder();
//                for (String ball : ballCode.code) {
//                    sb.append(ball);
//                }
//                list.add(sb.toString());
//            }
//        }
//        logger.info("ball={}", list);
//
//    }
}
