package com.nexio.autoball.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class SocketClient {
    private static final Logger logger = LoggerFactory.getLogger(SocketClient.class);
//    Executor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
//            60L, TimeUnit.SECONDS,
//            new SynchronousQueue<Runnable>());

    ScheduledExecutorService executor =
            Executors.newScheduledThreadPool(10);

    @Value("${socket.endpoint}")
    String endPoint;


    @Value("${socket.port}")
    int port;

    public void send(String message) {
        send(message,1);
    }

    public void send(String message , int delay) {
        executor.schedule(new NewSocketClient(endPoint, port, message), delay, TimeUnit.SECONDS);
    }

}
