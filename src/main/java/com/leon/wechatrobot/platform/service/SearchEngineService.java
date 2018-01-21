package com.leon.wechatrobot.platform.service;

import com.leon.wechatrobot.platform.model.Evidence;

import java.util.List;

/**
 * Created on 18/01/2018.
 *
 * @author Xiaolei-Peng
 */
public interface SearchEngineService {
    List<Evidence> search(String sentence);
}
