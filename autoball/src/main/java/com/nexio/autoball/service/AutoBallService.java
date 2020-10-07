package com.nexio.autoball.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.RetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@EnableRetry
public class AutoBallService {
    private static final Logger logger = LoggerFactory.getLogger(AutoBallService.class);

    @Retryable(value = {RetryException.class},//指定发生的异常进行重试
            maxAttempts = 3,                   //重试次数,默认即为3
            backoff = @Backoff(value = 2000))
    public void token() throws Exception {


    }

    @Retryable(value = {RetryException.class},//指定发生的异常进行重试
            maxAttempts = 3,                   //重试次数,默认即为3
            backoff = @Backoff(value = 2000))
    public void register() throws Exception {

    }

}