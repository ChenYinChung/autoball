package com.nexio.autoball.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 接收ABService的測試資料
 * 一併檢查header 及 傳送payload是否有誤
 */
@RestController
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);



    @PostMapping(path = "/api/v1/user/sync", consumes = "application/json", produces = "application/json")
    public String sync(@RequestHeader(name = "x-ca-key") String caKey,@RequestHeader(name = "x-ca-nonce") String caNonce,
                       @RequestHeader(name = "x-ca-timestamp") String caTimestamp,@RequestHeader(name = "x-ca-signature") String caSignature,
                       @RequestBody Map<String,String> payload) {
        logger.info(" sync Header x-ca-key={}",caKey);
        logger.info(" sync Header x-ca-nonce={}",caNonce);
        logger.info(" sync Header x-ca-timestamp={}",caTimestamp);
        logger.info(" sync Header x-ca-signature={}",caSignature);
        String usersJson = payload.get("users");

        return "OK";
    }

}
