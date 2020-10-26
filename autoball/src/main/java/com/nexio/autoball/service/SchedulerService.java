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

    static final String SETTING_ANT_PERCENT_BALL = "ant,0,0,0,0,0,1";
    static final String SETTING_ANT_FIVE_BALLS = "ant,1,1,1,1,1,0";

    //啟動彩金百分比天線延遲時間
    @Value("${delay.percent.ant:0}")
    int delayPercentAnt;
    //啟動彩金百分比開獎延遲時間
    @Value("${delay.percent.draw:5}")
    int delayPercentDraw;

    //啟動彩金2D3D天線延遲時間
    @Value("${delay.yeekee.ant:0}")
    int delayYeekeeAnt;
    //啟動彩金2D3D開獎延遲時間
    @Value("${delay.yeekee.draw:5}")
    int delayYeekeeDraw;

    @Autowired
    AutoBallService autoBallService;

    @Value("${draw.path}")
    String drawPath;

    @Autowired
    DrawRepo drawRepo;

    /**
     * JP
     *  0 0 0-4,07-23 * * *     表示每天，0到４，07到23點的0分0秒執行
     *  分別為二個動作
     *  第一部分由Scheduler發起，通知開球機，只開第六球
     *  第二部分由開球機發起，通知第六球結果，並發起http reqeust(/drawResult)
     *  http接收後，接續發起開球1-5的動作
     */
    @Async
    @Scheduled(cron = "3 0 0-4,7-23 * * *")
    public void scheduleSmallJackPot() {
        autoBallService.percent(SETTING_ANT_PERCENT_BALL,delayPercentAnt,delayPercentDraw);
    }

    /**
     * YEEKEE
     * 每7分在0-4,7-23執行
     */
    @Async
    @Scheduled(cron = "3 07 0-4,7-23 * * *")
    public void scheduleYeeKee7() {
        yeekee();
    }

    /**
     * YEEKEE
     * 每22分在0-3,6-23執行
     */
    @Async
    @Scheduled(cron = "3 22 0-3,6-23 * * *")
    public void scheduleYeeKee22() {
        yeekee();
    }

    /**
     * YEEKEE
     * 每37分在0-3,6-23執行
     */
    @Async
    @Scheduled(cron = "3 37 0-3,6-23 * * *")
    public void scheduleYeeKee37() {
        yeekee();
    }

    /**
     * YEEKEE
     * 每52分在0-3,6-23執行
     */
    @Async
    @Scheduled(cron = "3 52 0-3,6-23 * * *")
    public void scheduleYeeKee52(){
        yeekee();
    }

    private void yeekee(){
        autoBallService.yeekee(SETTING_ANT_FIVE_BALLS,delayYeekeeAnt,delayYeekeeDraw);
    }
    /**
     * PURGE
     * 04:22~06:07 不開
     */
    @Async
    @Scheduled(cron = "0 0 6 * * *")
    public void purge() {
        int delete = drawRepo.purge();
        logger.info("Purge data count[{}]",delete);
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
