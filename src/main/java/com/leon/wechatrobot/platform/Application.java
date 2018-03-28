package com.leon.wechatrobot.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Profile;

/**
 * Created on 27/03/2018.
 *
 * @author Xiaolei-Peng
 */

@Profile("dev")
@SpringBootApplication
@EnableCaching
@MapperScan("com.leon.wechatrobot.platform.dao")
//public class Application extends SpringBootServletInitializer {
public class Application {

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(Application.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
