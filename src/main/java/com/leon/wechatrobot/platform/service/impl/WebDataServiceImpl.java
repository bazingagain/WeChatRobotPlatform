package com.leon.wechatrobot.platform.service.impl;

import com.leon.wechatrobot.platform.model.Evidence;
import com.leon.wechatrobot.platform.service.SearchEngineService;
import com.leon.wechatrobot.platform.service.WebDataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created on 18/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class WebDataServiceImpl implements WebDataService {

    @Autowired
    private SearchEngineService baiduSearchService;

    @Autowired
    private SearchEngineService googleSearchService;

    @Autowired
    private SearchEngineService wikiSearchService;

    @Override
    public List<Evidence> getEvidenceFromBaidu(String question) {
        return baiduSearchService.search(question);
    }

    @Override
    public List<Evidence> getEvidenceFromGoogle(String question) {
        return googleSearchService.search(question);
    }

    @Override
    public List<Evidence> getEvidenceFromWiki(String question) {
        return wikiSearchService.search(question);
    }
}
