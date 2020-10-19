package com.nexio.autoball.controller;

import com.nexio.autoball.service.DrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


/**
 * 接收開獎球號
 */
@RestController
public class DrawController {
    @Autowired
    DrawService drawService;

    @PostMapping(path = "/draw", consumes = "application/json", produces = "application/json")
    public String draw(@RequestParam(value = "gameNum") String gameNum,
                                  @RequestParam(value = "drawNum") String drawNum) {
        drawService.draw(gameNum,drawNum);

        return "OK";

    }

}
