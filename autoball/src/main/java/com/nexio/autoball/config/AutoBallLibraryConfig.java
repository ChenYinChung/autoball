package com.nexio.autoball.config;

import autoball.AutoBallLibrary;
import com.sun.jna.Native;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoBallLibraryConfig {
    @Value("${lib.path}")
    String libPath;

    @Bean("autoBallLibrary")
    public AutoBallLibrary autoBallLibrary() {
//        String pwd = System.getProperty("user.dir");
//        String lib = pwd + "/go/autoballapi.so";
        AutoBallLibrary INSTANCE = (AutoBallLibrary) Native.loadLibrary(libPath, AutoBallLibrary.class);
        return INSTANCE;
    }

}
