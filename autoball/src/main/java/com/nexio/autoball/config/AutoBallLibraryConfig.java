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
        AutoBallLibrary INSTANCE = (AutoBallLibrary) Native.load(libPath, AutoBallLibrary.class);
        return INSTANCE;
    }

}
