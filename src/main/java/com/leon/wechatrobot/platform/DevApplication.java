package com.leon.wechatrobot.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Profile;

/**
 * Created on 27/03/2018.
 *
 * @author Xiaolei-Peng
 *
 * 开发环境,启动入口
 */

@Profile("dev")
@SpringBootApplication
@EnableCaching
@MapperScan("com.leon.wechatrobot.platform.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
