package com.nexio.autoball.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DrawService {
    private static final Logger logger = LoggerFactory.getLogger(DrawService.class);

    public String draw(String gameNum, String drawResult) {

        logger.info("GameNum[{}] drawNums[{}]",gameNum,drawResult);
        return "";
    }

}
