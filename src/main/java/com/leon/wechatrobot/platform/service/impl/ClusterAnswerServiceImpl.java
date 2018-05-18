package com.leon.wechatrobot.platform.service.impl;

import com.leon.wechatrobot.platform.dao.ClusterAnswerBeanMapper;
import com.leon.wechatrobot.platform.model.ClusterAnswerBean;
import com.leon.wechatrobot.platform.service.ClusterAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 13/04/2018.
 *
 * @author Xiaolei-Peng
 */

@Service
public class ClusterAnswerServiceImpl implements ClusterAnswerService {

    @Autowired
    private ClusterAnswerBeanMapper mapper;

    @Override
    public List<ClusterAnswerBean> listClusterAnswer(String question) {
        return mapper.listAllClusterAnswer();
    }
}
