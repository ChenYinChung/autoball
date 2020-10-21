package com.nexio;

import com.nexio.autoball.config.YamlPropertySourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.nexio.autoball"})
@EnableAsync
@EnableRetry
@EnableScheduling
//@Import({PersistenceConfig.class, RestTemplateConfig.class})
@PropertySource({"application.properties", "undertow.properties", "jdbc.properties","sql.properties"})
@PropertySource(factory = YamlPropertySourceFactory.class, value = {"classpath:autoball.yaml",})
public class WebApplication {

    /**
     * Start Web Application
     *
     * @param args
     */
    @SuppressWarnings("uncommentedmain")
    public static void main(String args[]) {
        //執行SpringApplication
        SpringApplication application = new SpringApplication(WebApplication.class);
//        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
    }
}