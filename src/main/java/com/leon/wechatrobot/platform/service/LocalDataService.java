package com.leon.wechatrobot.platform.service;

import com.leon.wechatrobot.platform.bean.QAPairBean;

import java.util.List;

/**
 * Created on 17/01/2018.
 *
 * @author Xiaolei-Peng
 */
public interface LocalDataService {

    QAPairBean getQAPair(Integer id);
    List<QAPairBean> listQAPair(String category);
}
