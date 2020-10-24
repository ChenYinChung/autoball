package com.nexio.autoball.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    @Bean(destroyMethod="shutdownNow")
    public ScheduledExecutorService taskExecutors() {
        return Executors.newScheduledThreadPool(100);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        //引數傳入一個執行緒池
        scheduledTaskRegistrar.setScheduler(taskExecutors());
    }

}