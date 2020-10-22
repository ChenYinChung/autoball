package com.nexio.autoball.service;

import com.nexio.autoball.component.SleClient;
import com.nexio.autoball.component.SocketClient;
import com.nexio.autoball.model.BallCode;
import com.nexio.autoball.model.BsArray;
import com.nexio.autoball.model.Draw;
import com.nexio.autoball.model.GameInfo;
import com.nexio.autoball.repo.DrawRepo;
import com.nexio.autoball.utils.GameInfoUtils;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    SleClient sleClient;


    /**
     * 送Request 至 AutoballController socket
     * @param request
     * @return
     * @throws IOException
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public String sendRequestToSocket(String request) throws IOException {
        String ret = socketClient.send(request);
        return ret;
    }

    /**
     * 初始化開獎期號
     * @param issue
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public void insertDraw(String issue){
        Draw draw = new Draw();
        draw.setGameNum(issue);
        draw.setDrawStatus(0);
        drawRepo.insert(draw);
    }

    /**
     * 接收開出內容並更新DB GameInfo ,　status
     * @param gameNum
     * @param drawResult
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public void draw(String gameNum, String drawResult) {
        GameInfo gameInfo = GameInfoUtils.parse(gameNum,drawResult);

        logger.info("開獎期號　gameNum src[{}]",gameNum);
        logger.info("開獎結果　drawResult src[{}]",drawResult);

        Draw draw =  drawRepo.findByGameNum(gameNum);
        draw.setGameInfo(gameInfo);
        draw.setDrawStatus(1);
        drawRepo.update(draw);
    }

    /**
     * 還沒確定送出資訊
     * @param gameNum
     * @param drawResult
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public void sendDrawResultToSLE(String gameNum, String drawResult) throws Exception {
        Map<String,String> map = new TreeMap<>();
        map.put("gameNum",gameNum);
        map.put("drawResult",drawResult);
        String message = sleClient.send(map);
        logger.info("SLE message[{}]",message);
    }




    public static void main(String[] arg) {
//        drawService.draw("201019001", "1,2,6_1,2,3;2,3,4;1,5,6");
//        drawService.draw("2010211445", "1,2,3,4,5_8;0;ABCD;4;Q");
    }
}