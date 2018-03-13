package com.leon.wechatrobot.platform.service;

import com.leon.wechatrobot.platform.model.CandidateAnswer;

import java.util.List;

/**
 * Created on 27/01/2018.
 *
 * @author Xiaolei-Peng
 */
public interface CandicateAnswerFilterService {
    void filter(String question, List<CandidateAnswer> candidateAnswers);
}
