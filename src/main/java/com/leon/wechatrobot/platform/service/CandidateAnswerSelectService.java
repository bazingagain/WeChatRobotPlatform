package com.leon.wechatrobot.platform.service;

import com.leon.wechatrobot.platform.model.Evidence;
import com.leon.wechatrobot.platform.model.Question;

/**
 * Created on 27/01/2018.
 *
 * @author Xiaolei-Peng
 */
public interface CandidateAnswerSelect {
    /**
     * 提取候选答案 候选答案存储在evidence对象里面
     *
     * @param question 问题
     * @param evidence 证据
     */
    public void select(Question question, Evidence evidence);
}
