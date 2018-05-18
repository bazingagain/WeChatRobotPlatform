package com.leon.wechatrobot.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Profile;

/**
 * Created on 2018/4/23.
 *
 * @author Xiaolei-Peng
 *
 * 部署环境, 启动入口
 * 主要是将项目打包成war包, 部署到线上的 Tomcat 容器中
 * 在打包成war包之前,需要改动Maven中的配置,排除掉spring-boot-starter-web 中的spring-boot-starter-tomcat
 * 因为在线上会使用线上的Tomcat,而不是使用项目本身的Tomcat插件
 */

@Profile("prod")
@SpringBootApplication
@EnableCaching
@MapperScan("com.leon.wechatrobot.platform.dao")
public class DeployApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DeployApplication.class);
    }

}
