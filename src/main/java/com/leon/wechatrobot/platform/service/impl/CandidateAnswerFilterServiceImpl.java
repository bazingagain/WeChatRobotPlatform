package com.leon.wechatrobot.platform.service.impl;

import com.leon.wechatrobot.platform.model.CandidateAnswer;
import com.leon.wechatrobot.platform.service.CandidateAnswerFilterService;
import com.leon.wechatrobot.platform.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;

/**
 * Created on 22/02/2018.
 *
 * @author Xiaolei-Peng
 *
 * 如果候选答案出现在问题中，则过滤
 */
public class CandidateAnswerFilterServiceImpl implements CandidateAnswerFilterService{

    @Autowired
    private QuestionService questionService;

    @Override
    public void filter(String question, List<CandidateAnswer> candidateAnswers) {
        //对问题分词
        List<String> questionWords = questionService.getWords(question, true);
        StringBuilder str = new StringBuilder();
        str.append("对问题分词: ");
        for (String questionWord : questionWords) {
            str.append(questionWord).append(" ");
        }
        //答案不能在问题中，去掉
        Iterator<CandidateAnswer> iterator = candidateAnswers.iterator();
        while (iterator.hasNext()) {
            CandidateAnswer candidateAnswer = iterator.next();
            if (questionWords.contains(candidateAnswer.getAnswer())) {
                iterator.remove();
            }
        }

    }
}
