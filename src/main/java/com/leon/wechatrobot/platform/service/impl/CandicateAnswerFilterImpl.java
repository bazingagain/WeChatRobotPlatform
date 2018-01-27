package com.leon.wechatrobot.platform.service.impl;

import com.leon.wechatrobot.platform.model.CandidateAnswer;
import com.leon.wechatrobot.platform.service.CandicateAnswerFilterService;
import com.leon.wechatrobot.platform.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created on 27/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class CandicateAnswerFilterImpl implements CandicateAnswerFilterService {

    @Autowired
    private QuestionService questionService;

    @Override
    public void filter(String question, List<CandidateAnswer> candidateAnswers) {

    }
}
