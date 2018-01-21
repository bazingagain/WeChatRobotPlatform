package com.leon.wechatrobot.platform.service;

import com.leon.wechatrobot.platform.model.Evidence;

import java.util.List;

/**
 * Created on 18/01/2018.
 *
 * @author Xiaolei-Peng
 */
public interface WebDataService {
    List<Evidence> getEvidenceFromBaidu(String question);
    List<Evidence> getEvidenceFromGoogle(String question);
    List<Evidence> getEvidenceFromWiki(String question);
}
