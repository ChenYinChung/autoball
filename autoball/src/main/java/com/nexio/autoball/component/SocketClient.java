package com.nexio.autoball.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class SocketClient {
    private static final Logger logger = LoggerFactory.getLogger(SocketClient.class);
    @Autowired
    ScheduledExecutorService scheduledExecutorService;

    @Value("${socket.endpoint}")
    String endPoint;


    @Value("${socket.port}")
    int port;

    public void send(String message) {
        send(message,1);
    }

    public void send(String message , int delay) {
        scheduledExecutorService.schedule(new NewSocketClient(endPoint, port, message), delay, TimeUnit.SECONDS);
    }

}
