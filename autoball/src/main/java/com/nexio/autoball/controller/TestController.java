package com.nexio.autoball.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nexio.autoball.utils.CryptoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@RestController
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Value("${callback.skey}")
    String skey;

    /**
     * 測試用，不與任何資料對接
     * @param data
     * @return
     */
    @PostMapping(path = "/autoball", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    public Boolean autoball(@RequestBody String data) throws Exception {
        logger.info("body[{}]",data);

        byte[] ecryptstr = Base64Utils.decodeFromString(data);
        //byte資料轉JSON
        JsonObject decodedData = JsonParser.parseString(new String(ecryptstr, StandardCharsets.UTF_8)).getAsJsonObject();
        SecretKeySpec skeySpec = CryptoUtils.generateAESKeySpec(skey);
        byte[] iv = Base64Utils.decodeFromString(decodedData.get("iv").getAsString());
        byte[] value = Base64Utils.decodeFromString(decodedData.get("value").getAsString());
        String decryptedString = CryptoUtils.aesDecrypt(skeySpec, iv, value);

        logger.info("decode string[{}]",decryptedString);

        return true;
    }

}
