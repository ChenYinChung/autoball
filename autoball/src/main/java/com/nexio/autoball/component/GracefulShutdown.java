package com.nexio.autoball.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * 优雅关闭 Spring Boot
 * curl -X POST http://localhost:8008/actuator/shutdown
 * nginx 必需allow ip指定，不能被外部關閉
 */
@Component
public class GracefulShutdown implements ApplicationListener<ContextClosedEvent>, ApplicationContextAware {
    private static Logger logger = LoggerFactory.getLogger(GracefulShutdown.class);

    @Autowired
    private GracefulShutdownWrapper gracefulShutdownWrapper;

    private ApplicationContext context;

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        gracefulShutdownWrapper.getGracefulShutdownHandler().shutdown();
        logger.info("Server graceful shutdown now");
        try {
            gracefulShutdownWrapper.getGracefulShutdownHandler().awaitShutdown();
            logger.info("Server graceful shutdown successed");
        } catch (InterruptedException e) {
            logger.error("Server graceful shutdown failure", e);
        }

        logger.info("ApplicationContext closed now");
        try {
            ((ConfigurableApplicationContext) context).close();
            logger.info("ApplicationContext closed successed");
        } catch (BeansException e) {
            logger.error("ApplicationContext closed failure", e);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        this.context = ctx;
    }
}
