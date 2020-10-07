package com.nexio.autoball.controller;

import autoball.AutoBallLibrary;
import com.nexio.autoball.service.AutoBallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 接收ABService的測試資料
 * 一併檢查header 及 傳送payload是否有誤
 */
@RestController
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    AutoBallService autoBallService;

    @PostMapping(path = "/startGame", consumes = "application/json", produces = "application/json")
    public String startGame(@RequestParam(value = "nGameCount", defaultValue = "1") int nGameCount,
                       @RequestParam(value = "nTimeSpan", defaultValue = "0") int nTimeSpan)  {
        autoBallService.startGame(nGameCount,nTimeSpan);
        return "OK";
    }

    @PostMapping(path = "/hasGamePlayed", consumes = "application/json", produces = "application/json")
    public Boolean hasGamePlayed()  {
        boolean isError = autoBallService.hasGamePlayed();
        return isError;
    }

    @PostMapping(path = "/getGameInfo", consumes = "application/json", produces = "application/json")
    public AutoBallLibrary.GameInfoStruct getGameInfo()  {
        AutoBallLibrary.GameInfoStruct gameInfoStruct = autoBallService.getGameInfo();
        return gameInfoStruct;
    }

    @PostMapping(path = "/terminateGame", consumes = "application/json", produces = "application/json")
    public Integer terminateGame()  {
        int isError = autoBallService.terminateGame();
        return isError;
    }

    @PostMapping(path = "/suspendGame", consumes = "application/json", produces = "application/json")
    public Integer suspendGame()  {
        int isError = autoBallService.suspendGame();
        return isError;
    }

    @PostMapping(path = "/resumeGame", consumes = "application/json", produces = "application/json")
    public Integer resumeGame()  {
        int isError = autoBallService.resumeGame();
        return isError;
    }

    @PostMapping(path = "/connectReader", consumes = "application/json", produces = "application/json")
    public Boolean connectReader(@RequestParam(value = "nCommNum", defaultValue = "1") int nCommNum,
                            @RequestParam(value = "laudrate ", defaultValue = "0") int laudrate)  {
        boolean isError = autoBallService.connectReader(nCommNum,laudrate);
        return isError;
    }

}
