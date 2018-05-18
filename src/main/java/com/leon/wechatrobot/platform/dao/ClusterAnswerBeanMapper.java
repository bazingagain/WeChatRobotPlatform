package com.leon.wechatrobot.platform.dao;

import generatortest.mode.ClusterAnswerBean;

public interface ClusterAnswerBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClusterAnswerBean record);

    int insertSelective(ClusterAnswerBean record);

    ClusterAnswerBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClusterAnswerBean record);

    int updateByPrimaryKey(ClusterAnswerBean record);
}