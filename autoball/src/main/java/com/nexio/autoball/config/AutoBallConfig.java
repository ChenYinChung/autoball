package com.nexio.autoball.config;

import autoball.AutoBallLibrary;
import com.sun.jna.Native;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;

/**
 * RestTemplate配置
 */
@Configuration
public class AutoBallConfig {

    @Value("${lib}")
    String lib;

    @Bean
    public AutoBallLibrary autoBallLibrary() {
        AutoBallLibrary INSTANCE = (AutoBallLibrary) Native.load(lib, AutoBallLibrary.class);
        return INSTANCE;
    }


}