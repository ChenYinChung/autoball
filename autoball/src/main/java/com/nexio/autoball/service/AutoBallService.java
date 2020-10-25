package com.nexio.autoball.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nexio.autoball.component.CmsClient;
import com.nexio.autoball.component.NewSocketClient;
import com.nexio.autoball.component.SocketClient;
import com.nexio.autoball.model.Draw;
import com.nexio.autoball.model.DrawType;
import com.nexio.autoball.repo.DrawRepo;
import com.nexio.autoball.utils.BallUtils;
import com.nexio.autoball.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 配合DLL操作API
 * 可以不用了，32bits的電腦操作
 */
@Service
@EnableRetry
public class AutoBallService {
    private static final Logger logger = LoggerFactory.getLogger(AutoBallService.class);
    @Autowired
    SocketClient socketClient;

    @Autowired
    DrawRepo drawRepo;

    @Autowired
    CmsClient cmsClient;

    /**
     * 初始化開獎期號
     *
     * @param issue
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public void insertDraw(String issue, DrawType drawType) throws JsonProcessingException {
        Draw draw = new Draw();
        draw.setGameNum(issue);
        draw.setBalls(new TreeMap<>());
        draw.setGameId(drawType);
        drawRepo.insert(draw);
    }

    /**
     * 接收開出內容並更新DB GameInfo ,　status
     *
     * @param gameNum
     * @param drawResult
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public void drawResult(String gameNum, String drawResult) throws Exception {
        Map<String, String> balls = BallUtils.parse(drawResult);
        Draw draw = drawRepo.findByGameNum(gameNum);

        //發生在手動執行AutoBall介面開獎
        if(draw == null){
            logger.error("無法找到對應期號[{}]",gameNum);
            return;
        }

        balls.putAll(draw.getBalls());
        draw.setBalls(balls);
        drawRepo.update(draw);

        //如果只有一個號碼6，則要呼叫jackpot
        //如果是五個號碼，更新DB後，call back cms

        if (balls.size() == 1 && balls.containsKey("6")) { //這是jp的百分比位置，還要呼叫2d,3d
            setAntenna(SchedulerService.SETTING_ANT_FIVE_BALLS,SchedulerService.DELAY_2D3D_ANT);
            drawAutoBall(gameNum,SchedulerService.DELAY_2D3D_DRAW);

        } else if((draw.getGameId().equals(DrawType.SMALLJACKPOT) && draw.getBalls().size()==6)
                || (draw.getGameId().equals(DrawType.YEEKEE) && draw.getBalls().size()==5)
        ) {
            draw = drawRepo.findByGameNum(gameNum);
            String message = cmsClient.send(draw);
            logger.info("SLE message[{}]", message);
        }
    }

    /**
     * 排程只有開第六球，
     * 其餘１－５球，由auto ball callback /drawresut才會開出
     */
    public void percent(String antenna , int antDelay , int drawDealy) {
        try {
            logger.info("自動排程－設定第6管");
            String gameNum = DateUtils.getIssue();
            insertDraw(gameNum, DrawType.SMALLJACKPOT);
            setAntenna(antenna,antDelay);
            drawAutoBall(gameNum,drawDealy);
        } catch (JsonProcessingException e) {
            logger.error("percent error", e);
        }
    }

    public void yeekee(String antenna , int antDelay , int drawDealy) {
        try {
            String gameNum = DateUtils.getIssue();
            insertDraw(gameNum, DrawType.YEEKEE);
            setAntenna(antenna,antDelay);
            drawAutoBall(gameNum,drawDealy);
        } catch (JsonProcessingException e) {
            logger.error("Yeekee error", e);
        }
    }

    /**
     * 設定天線及延遲
     * @param request
     * @param delay
     */
    void setAntenna(String request ,int delay){
        logger.info("自動排程－呼叫API變更天線delay[{}][{}]",delay,request);
        socketClient.send(request,delay);
    }

    /**
     * 啟動開球及延遲
     * @param gameNum
     * @param delay
     */
    void drawAutoBall(String gameNum,int delay){
        String request = "startGame," + gameNum + ",1,0";
        logger.info("自動排程－呼叫API開球delay[{}][{}]",delay,request);
        socketClient.send(request,delay);
    }
}