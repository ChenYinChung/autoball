package com.nexio.autoball.controller;

import com.nexio.autoball.service.AutoBallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutoBallController {
    private static final Logger logger = LoggerFactory.getLogger(AutoBallController.class);

    @Autowired
    AutoBallService autoBallService;

    /**
     * "2010211445", "1,2,3,4,5_8;0;ABCD;4;Q"
     * underline 之前是筒號，之後是開出內容
     * @param gameNum
     * @param drawResult
     */
    @PostMapping(path = "/drawResult", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    public void draw(@RequestParam(value = "gameNum") String gameNum,
                     @RequestParam(value = "drawResult") String drawResult) throws Exception {
        autoBallService.drawResult(gameNum,drawResult);

    }

    @PostMapping(path = "/find", consumes = "application/json", produces = "application/json")
    public void find(@RequestParam(value = "gameNum") String gameNum) {
    }

}
