package com.nexio.autoball.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexio.autoball.check.CheckGameInfo;
import com.nexio.autoball.component.SocketClient;
import com.nexio.autoball.model.Draw;
import com.nexio.autoball.repo.DrawRepo;
import com.nexio.autoball.utils.DateUtils;
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
    AutoBallService autoBallService;

    @Value("${draw.path}")
    String drawPath;

    @Autowired
    DrawRepo drawRepo;

    /**
     * JP
     *  0 0 07-23 * * *     表示每天，07點到23點的0分0秒執行
     *  第一組
     */
    @Async
    @Scheduled(cron = "3 0 7-23,0-4 * * *")
    public void scheduleSmallJackPotPartOne() {
        autoBallService.percent();
    }

    /**
     * YEEKEE
     * 04:22~06:07 不開
     */
    @Async
    @Scheduled(cron = "3 07 7-23,0-4 * * *")
    public void scheduleYeeKee7PartOne() {
        autoBallService.yeekee();
    }

    /**
     * YEEKEE
     * 04:22~06:07 不開
     */
    @Async
    @Scheduled(cron = "3 22 6-23,0-3 * * *")
    public void scheduleYeeKee22PartOne() {
        autoBallService.yeekee();
    }

    /**
     * YEEKEE
     * 04:22~06:07 不開
     */
    @Async
    @Scheduled(cron = "3 37 6-23,0-3 * * *")
    public void scheduleYeeKee37PartOne() {
        autoBallService.yeekee();
    }

    /**
     * YEEKEE
     * 04:22~06:07 不開
     */
    @Async
    @Scheduled(cron = "3 52 6-23,0-3 * * *")
    public void scheduleYeeKee52PartOne() {
        autoBallService.yeekee();
    }


    /**
     * PURGE
     * 04:22~06:07 不開
     */
    @Async
    @Scheduled(cron = "0 0 6 * * *")
    public void purge() {
        int delete = drawRepo.purge();
    }

//    /**
//     * 檢核DB&檔案結果
//     */
//    @Async
//    @Scheduled(cron = "0 2,7,12,17,22,27,32,37,42,47,52,57 * * * *")
//    public void checkBalls() {
//        try {
//
//            Date rightNow = new Date();   //當前時間
//            Calendar calendar = Calendar.getInstance(); //得到日曆
//            calendar.setTime(rightNow);//把當前時間賦給日曆
//            File test = new File(DateUtils.checkIssue(drawPath,rightNow));
//            List<String> jsons = FileUtils.readLines(test, Charset.forName("UTF-8"));
//
//            for(String json : jsons){
//                ObjectMapper objectMapper =  new ObjectMapper();
//                CheckGameInfo gameInfo = objectMapper.readValue(json, CheckGameInfo.class);
//                // if check in db status in running ,apply
//                // call DrawService call back to SLE-CMS service
//                //logger.info("自動排程－檢核檔案開獎結果 {}", json);
//
//                Draw draw = drawRepo.findByGameNum(""+gameInfo.nGameNum);
//
//                if(draw==null){
//                    logger.error("Draw game num not found[{}]",gameInfo.nGameNum);
//                }else{
//                    logger.error("Draw game num json[{}]",draw);
//                }
//
//            }
//        } catch (Exception e) {
//            logger.error("Task error", e);
//        }
//
//    }
}
