package com.leon.wechatrobot.platform.config;

import com.leon.wechatrobot.platform.service.BaikeService;
import com.leon.wechatrobot.platform.service.SearchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 28/03/2018.
 *
 * @author Xiaolei-Peng
 */

@Configuration
public class CustomBeanConfig {

    @Resource(name = "baiduBaikeService")
    private BaikeService baiduBaikeService;

    @Resource(name = "cndbBaikeService")
    private BaikeService cndbBaikeService;

    @Resource(name = "hudongBaikeService")
    private BaikeService hudongBaikeService;

    @Resource(name = "baiduSearchService")
    private SearchService baiduSearchService;

    @Resource(name = "bingSearchService")
    private SearchService bingSearchService;

    @Resource(name = "soSearchService")
    private SearchService soSearchService;

    @Bean
    public List<BaikeService> baikeServices() {
        List<BaikeService> baikeServices = new ArrayList<>();
        baikeServices.add(baiduBaikeService);
        baikeServices.add(cndbBaikeService);
        baikeServices.add(hudongBaikeService);
        return baikeServices;
    }

    @Bean
    public List<SearchService> searchServices() {
        List<SearchService> searchServices = new ArrayList<>();
        searchServices.add(baiduSearchService);
        searchServices.add(bingSearchService);
        searchServices.add(soSearchService);
        return searchServices;
    }
    
}
