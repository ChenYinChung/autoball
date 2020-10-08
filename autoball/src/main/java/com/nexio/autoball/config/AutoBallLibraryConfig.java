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
        String pwd = System.getProperty("user.dir");
        System.setProperty("java.library.path", pwd);

        String lib = pwd + libPath;
        AutoBallLibrary INSTANCE = (AutoBallLibrary) Native.loadLibrary(lib, AutoBallLibrary.class);
        return INSTANCE;
    }

}
