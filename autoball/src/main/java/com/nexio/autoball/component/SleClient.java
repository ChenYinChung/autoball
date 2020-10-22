package com.nexio.autoball.component;


import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class SleClient {
    private static final Logger logger = LoggerFactory.getLogger(SocketClient.class);
    @Value("${callback.endpoint}")
    String endPoint;

    @Autowired
    RestTemplate restTemplate;

    public String send(Map<String,String> message){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        logger.info("sendEndPointUrl request json {}",message);
        JSONObject json = new JSONObject();
        message.forEach((k,v)->json.put(k,v));
        //填入hedaer & body
        HttpEntity<String> entity = new HttpEntity<String>(json.toJSONString(),headers);

        ResponseEntity<String> response = restTemplate.exchange(endPoint, HttpMethod.POST, entity, String.class);
        return response.getBody();
    }
}
