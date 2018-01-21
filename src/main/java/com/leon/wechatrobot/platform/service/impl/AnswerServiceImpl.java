package com.leon.wechatrobot.platform.service.impl;

import com.leon.wechatrobot.platform.bean.QAPairBean;
import com.leon.wechatrobot.platform.service.AnswerService;
import com.leon.wechatrobot.platform.service.LocalDataService;
import com.leon.wechatrobot.platform.service.QuestionService;
import com.leon.wechatrobot.platform.util.common.SimilarityUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created on 17/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private LocalDataService localDataService;

    @Override
    public String getAnswer(String question) {
        //TODO
        String category = questionService.classify(question);
        String answer = getBestAnswerFromLocal(question, category);
        if (answer == null) {
            answer = getBestAnswerFromWeb(question, category);
        }
        return answer;
    }

    @Override
    public String getBestAnswerFromLocal(String question, String category) {
        double threshold = 0.6;
        double bestSimilarity = Double.MIN_VALUE;
        String bestAnswer = null;
        if (questionService.getAllQuestionCategory().contains(category)) {
            List<QAPairBean> questions = localDataService.listQAPair(category);
            for (QAPairBean qaPair : questions) {
                double similarity = SimilarityUtil.sentenceSimilarity(question, qaPair.getQuestion());
                if (similarity > bestSimilarity) {
                    bestSimilarity = similarity;
                    bestAnswer = qaPair.getAnswer();
                }
            }
        }
        if (bestSimilarity >= threshold) {
            return bestAnswer;
        }
        return null;
    }

    @Override
    public String getBestAnswerFromWeb(String question, String category) {

        return null;
    }

    @Override
    public String getCandicateAnswerFromWeb(String question) {

        return null;
    }
}
