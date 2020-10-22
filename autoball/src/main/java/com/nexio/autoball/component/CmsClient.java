package com.nexio.autoball.component;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexio.autoball.model.Draw;
import com.nexio.autoball.utils.CryptoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Component
public class CmsClient {
    private static final Logger logger = LoggerFactory.getLogger(SocketClient.class);
    @Value("${cms.endpoint}")
    String endPoint;

    @Value("${cms.skey}")
    String skey;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public String send(Draw draw) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        SecretKeySpec skeySpec = CryptoUtils.generateAESKeySpec(skey);
        byte[] iv = new byte[128 / 8];
        SecureRandom prng = new SecureRandom();
        prng.nextBytes(iv);

        byte[] encryptedData = CryptoUtils.aesEncrypt(skeySpec, iv, objectMapper.writeValueAsString(draw));
        Map<String, Object> map = new HashMap<>();
        map.put("iv", iv);
        map.put("value", encryptedData);
        map.put("mac", "509e641eb94ccda29dc8d873e32e33836aacc7264af20da20687abb906bd9696");

        String data = Base64Utils.encodeToString(objectMapper.writeValueAsString(map).getBytes());
//        //填入hedaer & body
        HttpEntity<String> entity = new HttpEntity<String>(data,headers);

        ResponseEntity<String> response = restTemplate.exchange(endPoint, HttpMethod.POST, entity, String.class);
        return response.getBody();
    }
}
