package com.nexio.autoball.controller;

import autoball.AutoBallLibrary;
import com.nexio.autoball.service.AutoBallService;
import com.sun.jna.ptr.PointerByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接收ABService的測試資料
 * 一併檢查header 及 傳送payload是否有誤
 */
@RestController
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    AutoBallService autoBallService;

    @PostMapping(path = "/init", consumes = "application/json", produces = "application/json")
    public Boolean init() {
        boolean isError = autoBallService.init();
//        autoBallService.connectReader(5, 115200);
//        autoBallService.connectRD1(3,9600);
//        autoBallService.connectRD2(4,9600);
//        autoBallService.getAntennaPara();
//        autoBallService.getControlProcess();
//        autoBallService.setControlStyle(1);
        return isError;
    }

    @PostMapping(path = "/gameStr", consumes = "application/json", produces = "application/json")
    public String gameStr() {
        String s = autoBallService.gameStr();
        return s;
    }

    @PostMapping(path = "/close", consumes = "application/json", produces = "application/json")
    public Boolean close() {
        boolean isError = autoBallService.disconnectReader();
        autoBallService.disconnectRD1();
        autoBallService.disconnectRD2();
       return isError;
    }


    @PostMapping(path = "/startGame", consumes = "application/json", produces = "application/json")
    public Boolean startGame(@RequestParam(value = "nGameCount", defaultValue = "1") int nGameCount,
                            @RequestParam(value = "nTimeSpan", defaultValue = "0") int nTimeSpan,
                            @RequestParam(value = "nCurGameNum", defaultValue = "1") int nCurGameNum) {
        Boolean isError = autoBallService.startGame(nGameCount, nTimeSpan,nCurGameNum);
        return isError;
    }

    @PostMapping(path = "/hasGamePlayed", consumes = "application/json", produces = "application/json")
    public Boolean hasGamePlayed() {
        boolean isError = autoBallService.hasGamePlayed();
        return isError;
    }

    @PostMapping(path = "/getGameInfo", consumes = "application/json", produces = "application/json")
    public AutoBallLibrary.GameInfoStruct getGameInfo() {
        AutoBallLibrary.GameInfoStruct gameInfoStruct = autoBallService.getGameInfo();
        return gameInfoStruct;
    }

    @PostMapping(path = "/terminateGame", consumes = "application/json", produces = "application/json")
    public Integer terminateGame() {
        int isError = autoBallService.terminateGame();
        return isError;
    }

    @PostMapping(path = "/suspendGame", consumes = "application/json", produces = "application/json")
    public Integer suspendGame() {
        int isError = autoBallService.suspendGame();
        return isError;
    }

    @PostMapping(path = "/resumeGame", consumes = "application/json", produces = "application/json")
    public Integer resumeGame() {
        int isError = autoBallService.resumeGame();
        return isError;
    }

    @PostMapping(path = "/connectReader", consumes = "application/json", produces = "application/json")
    public Boolean connectReader(@RequestParam(value = "nCommNum", defaultValue = "5") int nCommNum,
                                 @RequestParam(value = "laudrate ", defaultValue = "115200") int laudrate) {
        boolean isSuccess = autoBallService.connectReader(nCommNum, laudrate);
        return isSuccess;
    }

    @PostMapping(path = "/connectRD1", consumes = "application/json", produces = "application/json")
    public Boolean connectRD1(@RequestParam(value = "nCommNum", defaultValue = "3") int nCommNum,
                              @RequestParam(value = "laudrate ", defaultValue = "9600") int laudrate) {
        boolean isError = autoBallService.connectRD1(nCommNum, laudrate);
        return isError;
    }

    @PostMapping(path = "/connectRD2", consumes = "application/json", produces = "application/json")
    public Boolean connectRD2(@RequestParam(value = "nCommNum", defaultValue = "4") int nCommNum,
                              @RequestParam(value = "laudrate ", defaultValue = "9600") int laudrate) {
        boolean isError = autoBallService.connectRD2(nCommNum, laudrate);
        return isError;
    }

    @PostMapping(path = "/disconnectReader", consumes = "application/json", produces = "application/json")
    public Boolean disconnectReader() {
        boolean isError = autoBallService.disconnectReader();
        return isError;
    }

    @PostMapping(path = "/disconnectRD1", consumes = "application/json", produces = "application/json")
    public Boolean disconnectRD1() {
        boolean isError = autoBallService.disconnectRD1();
        return isError;
    }

    @PostMapping(path = "/disconnectRD2", consumes = "application/json", produces = "application/json")
    public Boolean disconnectRD2() {
        boolean isError = autoBallService.disconnectRD2();
        return isError;
    }

    @PostMapping(path = "/getLastError", consumes = "application/json", produces = "application/json")
    public Integer getLastError() {
        Integer isError = autoBallService.getLastError();
        return isError;
    }

    @PostMapping(path = "/getAntennaPara", consumes = "application/json", produces = "application/json")
    public Boolean getAntennaPara() {
        boolean isError = autoBallService.getAntennaPara();
        return isError;
    }

    @PostMapping(path = "/setAntennaPara", consumes = "application/json", produces = "application/json")
    public Boolean setAntennaPara() {
        boolean isError = autoBallService.setAntennaPara();
        return isError;
    }

    @PostMapping(path = "/getControlProcess", consumes = "application/json", produces = "application/json")
    public Boolean getControlProcess() {
        boolean isError = autoBallService.getControlProcess();
        return isError;
    }

    @PostMapping(path = "/setControlProcess", consumes = "application/json", produces = "application/json")
    public Boolean setControlProcess() {
        boolean isError = autoBallService.setControlProcess();
        return isError;
    }

    @PostMapping(path = "/setControlStyle", consumes = "application/json", produces = "application/json")
    public Boolean setControlStyle(@RequestParam(value = "nControlStyle", defaultValue = "1") int nControlStyle) {
        boolean isError = autoBallService.setControlStyle(nControlStyle);
        return isError;
    }
}